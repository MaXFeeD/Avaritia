var holerender = new Render();
var holemesh = new RenderMesh(__dir__ + "models/blackhole.obj", "obj", {
	scale: [0.0675, 0.0675, 0.0675], translate: [0, -2.4, 0]
});
holerender.getPart("body").setMesh(holemesh);

var GapingVoid = function() {
	this.RENDER = holerender;
	this.SKIN = "mob/black_hole.png";
	this.POOT_RANGE = 0.2;
	this.PARTICLES = Native.ParticleType.portal;
	this.PARTICLE_SPEED = 4.5;
//	this.PARTICLE_COUNT = 10;
	this.PARTICLE_COUNT = 1;
	
	this.RADIUS_SCALE = 0.5;
	this.COLLAPSE = 0.95;
	this.EXPLODE_RADIUS = 6.0;
	this.HOLE_DAMAGE = 3;
	this.POWER_SCALE = 0.075;
	this.SUCK_RANGE = 20.0;
	
	this.LIFE_TIME = 186;
	this.SPAWN_SCALE = 0;
	this.SPAWN_SKIN = "mob/void.png";
	this.SPAWN_PARTICLE_SCALE = 2;
	this.SPAWN_PARTICLE_RADIUS = 3.0;
};

GapingVoid.prototype.getVoidScale = function() {
	var life = this.age / this.LIFE_TIME, curve;
	if (life < this.COLLAPSE) curve = 0.005 + this.ease(1.0 - (this.COLLAPSE - life) / this.COLLAPSE) * 0.995;
	else curve = this.ease(1.0 - (life - this.COLLAPSE) / (1.0 - this.COLLAPSE));
	return 10.0 * curve;
};

GapingVoid.prototype.ease = function(input) {
	var t = (input || 0) - 1.0;
	return Math.sqrt(1.0 - t * t);
};

GapingVoid.prototype.isCollapsed = function() {
	return this.age / this.LIFE_TIME >= this.COLLAPSE;
};

GapingVoid.prototype.updateScale = function(age) {
	if (age < this.LIFE_TIME) {
		this.age = age, this.scale = this.getVoidScale(age);
		this.radius = this.scale * this.RADIUS_SCALE;
		this.pootdir = this.radius - this.POOT_RANGE;
		this.nomrange = this.radius * this.COLLAPSE;
		this.blockrange = Math.round(this.nomrange);
		var content = { scale: this.pootdir };
		if (this.collapsed != this.isCollapsed()) {
			if (!this.isCollapsed()) content.skin = this.SKIN;
			else content.skin = this.SPAWN_SKIN;
			this.collapsed = this.isCollapsed();
		}
		this.animation.describe(content);
	} else {
		this.animation.isLoaded && this.animation.destroy();
		World.explode(this.coords.x, this.coords.y, this.coords.z,
						this.EXPLODE_RADIUS, false);
	}
};

GapingVoid.prototype.spawnParticles = function(range) {
	for (var i = 0; i < this.PARTICLE_COUNT; ++i) {
		var radius = range || this.pootdir / 4;
		var u = Math.random(), v = Math.random();
		var theta = 2 * Math.PI * u, phi = Math.acos(2 * v - 1);
		var px = radius * Math.sin(phi) * Math.cos(theta);
		var py = radius * Math.sin(phi) * Math.sin(theta);
		var pz = radius * Math.cos(phi);
		var sx = px * this.PARTICLE_SPEED;
		var sy = py * this.PARTICLE_SPEED;
		var sz = pz * this.PARTICLE_SPEED;
		Particles.addParticle(this.PARTICLES, this.coords.x + px,
			this.coords.y + py, this.coords.z + pz, sx, sy, sz);
	}
};

GapingVoid.prototype.suckAndDamage = function() {
	var entities = EntityDataRegistry.getAllData(), hole = this;
	entities[Player.get()] = { type: Native.EntityType.PLAYER };
	Object.keys(entities).forEach(function(ent, index) {
		if (typeof ent != "number") ent = parseInt(ent);
		if (!ent /* null */ || !Entity.isExist(ent)) return;
		if (ITEM_TYPES.isItem(entities[ent].type)) return;
		var position = Entity.getPosition(ent);
		var dx = hole.coords.x - position.x, dy = hole.coords.y -
			position.y, dz = hole.coords.z - position.z;
		var lensquared = dx * dx + dy * dy + dz * dz;
		var len = Math.sqrt(lensquared);
		var lenn = len / hole.SUCK_RANGE;
		if (len > hole.SUCK_RANGE) return;
		if (len <= hole.nomrange && hole.HOLE_DAMAGE)
			Entity.damageEntity(ent, hole.HOLE_DAMAGE);
		var strength = (1.0 - lenn) * (1.0 - lenn);
		var power = hole.POWER_SCALE * hole.radius;
		Entity.addVelocity(ent, dx / len * strength * power,
							dy / len * strength * power,
							dz / len * strength * power);
	});
};

GapingVoid.prototype.eatBlocks = function() {
	if (this.age % 10 == 0) {
		var bx = Math.floor(this.coords.x),
			by = Math.floor(this.coords.y),
			bz = Math.floor(this.coords.z);
		for (var blockrange = this.blockrange, y = -blockrange; y <= blockrange; ++y) {
			for (var z = -blockrange; z <= blockrange; ++z) {
				for (var x = -blockrange; x <= blockrange; ++x) {
					var lx = bx + x, ly = by + y, lz = bz + z;
					if (ly >= 0 && ly <= 255) {
						var dist = Math.sqrt(x * x + y * y + z * z);
						if (dist <= this.nomrange) World.setBlock(lx, ly, lz, 0);
					}
				}
			}
		}
	}
};

GapingVoid.prototype.initialize = function(ticks) {
	this.animation = new Animation.Base(this.coords.x, this.coords.y, this.coords.z);
	this.animation.describe({ render: this.RENDER.getId(),
		skin: this.SPAWN_SKIN, scale: this.SPAWN_SCALE });
	var hole = this, ticks = ticks >= 0 ? ticks : 0;
	this.animation.loadCustom(function() {
		if (!this.ticks) this.ticks = ticks;
		hole.updateScale(++this.ticks);
		hole.spawnParticles();
		hole.suckAndDamage();
		hole.eatBlocks();
	});
};

GapingVoid.prototype.suck = function(coords) {
	this.coords = coords, this.initialize();
	for (var i = 0; i < this.SPAWN_PARTICLE_SCALE; i++)
		this.spawnParticles(this.SPAWN_PARTICLE_RADIUS);
	this.sound = new Sound("gapingVoid.ogg");
	this.sound.setInBlock(this.coords, 8);
	this.sound.play();
};

var ITEM_TYPES = [Native.EntityType.ITEM, Native.EntityType.EGG, Native.EntityType.ARROW, Native.EntityType.SMALL_FIREBALL, Native.EntityType.SNOWBALL, Native.EntityType.EXPERIENCE_ORB];
ITEM_TYPES.isItem = function(id) { return this.indexOf(id) != -1; };

function handleGapingVoid(coords) {
	new GapingVoid().suck(coords);
}

Callback.addCallback("ProjectileHit", function(projectile, item, target, coords) {
	if (Game.getGameMode() != 1) Player.decreaseCarriedItem(1);
	handleGapingVoid(Entity.getPosition(projectile));
});
