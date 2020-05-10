var BHMesh = new RenderMesh(__dir__ + "models/blackhole.obj", "obj", {
	scale: [0.1, 0.1, 0.1], translate: [0, -1.2, 0]
});
var BHRender = new Render();
var BHPart = BHRender.getPart("head");
BHPart.setMesh(BHMesh);
var BHtexture= new Texture("mob/BHtexture.png").setResolution(256, 256);

var ModelBH = new EntityModel();
ModelBH.setRender(BHRender);
ModelBH.setTexture(BHtexture);

var BlackHole = MobRegistry.registerEntity("BlackHole");
BlackHole.customizeVisual({
	getModels: function() {
		return { main: ModelBH };
	}
});
BlackHole.customizeDescription({
	getHitbox: function() {
		return { w: 0, h: 0 };
	}
});

IDRegistry.genItemID("customEntitySpawn");
Item.createItem("customEntitySpawn", "Spawn custom entity", {
	name: "stick"
});
Item.registerUseFunction("customEntitySpawn", function(coords, item, block) {
	Entity.spawnCustom("BlackHole", coords.relative.x + .5, coords.relative.y + .5, coords.relative.z + .5);
});

var endparticle = Particles.registerParticleType({
	texture: "ep", render: 2, size: [1, 1],
	lifetime: [40, 40], velocity: [0, -0.1, 0]
});

function handleExplode(coords, time) {
	Threading.initThread("explode", function() {
		java.lang.Thread.sleep(time * 50);
		Entity.remove("BlackHole");
		World.explode(coords.x, coords.y, coords.z, 7, false);
	}, 0);
}

Callback.addCallback("ProjectileHit", function(projectile, item, target, coords) {
	Player.setCarriedItem(item.id, item.count - 1, 0);
	coords = Entity.getPosition(projectile);
	var fmobs = Entity.getAllInRange(coords, 8);
	Entity.spawnCustom("BlackHole", coords.x, coords.y, coords.z);
	for (var i in fmobs) {
		Entity.moveToTarget(fmobs[i], coords.x, coords.y, coords.z,
				{ speed: 1, denyY: false, jumpVel: 0 });
	}
	for (var dx = -5; dx < 5; dx++) {
		for (var dy = -3; dy < 5; dy++) {
			for (var dz = -5; dz < 5; dz++) {
				if(dx * dx + dy * dy + dz * dz <= 20) {
					Particles.addParticle(endparticle, coords.x + 0.5 + dx,
						coords.y + 1 + dy, coords.z + 0.5 + dz, 0, -0.1, 0, 1);
				}
			}
		}
	};
	handleExplode(coords, 20 * 9);
});
