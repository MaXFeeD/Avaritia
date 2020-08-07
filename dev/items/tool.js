
IDRegistry.genItemID("cosmSword");
Item.createItem("cosmSword", "Sword of the Cosmos", {
	name: "cosm_sword", meta: 0
}, { stack: 1 });
Item.setEnchantType("cosmSword", 16, 999999999);

ToolAPI.addToolMaterial("cosmsw", {
	durability: 999999999,
	level: 7, efficiency: 6,
	damage: 99999999999999,
	enchantability: 14
});
ToolAPI.setTool(ItemID.cosmSword, "cosmsw", ToolType.sword);
Item.setToolRender(ItemID.cosmSword, true);

IDRegistry.genItemID("skull_sword");
Item.createItem("skull_sword", "Skullfire sword", {
	name: "skull_sword", meta: 0
}, { stack: 1 });
Item.setEnchantType("skull_sword", 14, 25);

ToolAPI.addToolMaterial("skull_sword", {
	durability: 999999999,
	level: 1, efficiency: 6,
	damage: 7, enchantability: 14
});
ToolAPI.setTool(ItemID.skull_sword, "skull_sword", ToolType.sword);
Item.setToolRender(ItemID.skull_sword, true);

Callback.addCallback("EntityDeath", function(victim) {
	if (Player.getCarriedItem().id == ItemID.skull_sword) {
		var type = Entity.getType(victim);
		if (type == 48 /* WITHER */ || type == Native.EntityType.SKELETON) {
			var coords = Entity.getPosition(victim);
			World.drop(coords.x, coords.y, coords.z, 397, 1, 1);
		}
	}
});


let cosmBowAnimator = new ItemAnimator();
let cosmCrosshair = new Crosshair();
let cosmArrow = new Arrow();
let cosmBow = new Bow();


cosmBowAnimator.Create({
	name: 'CosmAnimator',
	defaultTexture: 'bow_idle',
	defaultTextureData: 0,
	animtations: {
		Bowanim1: {
			6: {texture: "bow_pull", data: 0},
			12: {texture: "bow_pull", data: 1},
			18: {texture: "bow_pull", data: 2},
			24: {texture: "bow_pull", data: 3}
		},
		Bowanim2: {
			6: {texture: "bow_pull", data: 0},
			12: {texture: "bow_pull", data: 1},
			18: {texture: "bow_pull", data: 2},
			24: {texture: "bow_pull", data: 3}
		}
	}
});

cosmArrow.Create({
	namedID: "arrow",
	name: "Heawen Arrow",
	texture: "arrow",
	data: 0,
	skin: "mob/heavenarrow",
	particle: "heavenarrow",
	damage: 80,
	speed: 40,
	stack: 1,
	inCreative: false
});

cosmCrosshair.setUI();
cosmBow.Create({
	namedID: "infbow",
	name: "Cosm Bow",
	baseTexture: "bow_idle",
	baseTextureData: 0,
	maxDamage: 256,
	crosshair: cosmCrosshair,
	animation: cosmBowAnimator,
	sound: "Bow.ogg",
	arrows: {
		Bowarrow1: {
			arrow: cosmArrow,
			startUseAnimation: 'Bowanim1',
			endUseAnimation: 'Bowanim2'
		},
	}
});


/*
var INFINITE_BOW_ARROW_SHOT_COUNT = 16;

Callback.addCallback("BowArrowHit", function(projectile, item, target, coords) {
	if (projectile.id == ItemID.infbow && item.id == ItemID.infbow) {
		for (var i = 0; i <= INFINITE_BOW_ARROW_SHOT_COUNT; i++) {
			Entity.spawn(coords.x + 0.5 + Math.random() / 0.3, coords.y + 3, coords.z + 1.5 + Math.random()/0.3, 80, [heavenarrow]);
		}
	}
});*/

IDRegistry.genItemID("cosmPickaxe");
Item.createItem("cosmPickaxe", "World Braker", {
	name: "infpickaxe", meta: 0
}, { stack: 1 });
Item.setEnchantType("cosmPickaxe", 18, 10);

ToolAPI.addToolMaterial("cosmpi", {
	durability: 999999999,
	level: 10, efficiency: 10,
	damage: 7, enchantability: 14
});
ToolAPI.setTool(ItemID.cosmPickaxe, "cosmpi", ToolType.pickaxe);
Item.setToolRender(ItemID.cosmPickaxe, true);

IDRegistry.genItemID("cosmhammer");
Item.createItem("cosmhammer", "hammer", {
	name: "infhammer", meta: 0
}, { stack: 1 });
ToolAPI.setTool(ItemID.cosmhammer, "cosmpi", ToolType.pickaxe);
Item.setToolRender(ItemID.cosmhammer, true);

Callback.addCallback("DestroyBlock", function(coords, block, player) {
	if (Player.getCarriedItem().id == ItemID.cosmhammer) {
		var side = coords.side, x = 8, y = 9, z = 7;
		if (side == 4 || side == 5) x = 0;
		if (side == 1 || side == 6) y = 0;
		if (side == 2 || side == 3) z = 0;
		for (var xx = coords.x - x; xx <= coords.x + x; xx++) {
			for (var yy = coords.y - y; yy <= coords.y + y; yy++) {
				for (var zz = coords.z - z; zz <= coords.z + z; zz++) {
					if (World.getBlockID(xx, yy, zz) !== 7) {
						checkClusterable(xx, yy, zz);
						World.setBlock(xx, yy, zz, 0);
					}
				}
			}
		}
	}
});

IDRegistry.genItemID("cosmShovel");
Item.createItem("cosmShovel", "Planet Eater", {
	name: "infshovel", meta: 0
}, { stack: 1 });

ToolAPI.addToolMaterial("cosmsh", {
	durability: 999999999,
	level: 8, efficiency: 8,
	damage: 10,
	enchantability: 14
});
ToolAPI.setTool(ItemID.cosmShovel, "cosmsh", ToolType.shovel);
Item.setToolRender(ItemID.cosmShovel, true);

IDRegistry.genItemID("cosmdes");
Item.createItem("cosmdes", "destroyer", {
	name: "infdestroyer", meta: 0
}, { stack: 1 });
ToolAPI.setTool(ItemID.cosmdes, "cosmsh", ToolType.shovel);
Item.setToolRender(ItemID.cosmdes, true);

var DESTROYER_BLOCKS = [14, 15, 16, 21, 56, 73, 129, 49, 7];
DESTROYER_BLOCKS.hasId = function(id) {
	return this.indexOf(id) != -1;
};

var AXE_BLOCKS = [17, 18, 31, 38];
AXE_BLOCKS.hasId = function(id) {
	return this.indexOf(id) != -1;
};


Callback.addCallback("DestroyBlock", function(coords, block, player) {
	if(Player.getCarriedItem().id == ItemID.cosmdes) {
		var side = coords.side, x = 8, y = 9, z = 7;
		if (side == 4 || side == 5) x = 0;
		if (side == 1 || side == 6) y = 0;
		if (side == 2 || side == 3) z = 0;
		for (var xx = coords.x - x; xx <= coords.x + x; xx++) {
			for (var yy = coords.y - y; yy <= coords.y + y; yy++) {
				for (var zz = coords.z - z; zz <= coords.z + z; zz++) {
					if (!DESTROYER_BLOCKS.hasId(World.getBlockID(xx, yy, zz))) {
						
						World.setBlock(xx, yy, zz, 0);
					}
				}
			}
		}
	}
});

IDRegistry.genItemID("cosmAxe");
Item.createItem("cosmAxe", "Nature's Ruin", {
	name: "infaxe", meta: 0
}, { stack: 1 });

ToolAPI.addToolMaterial("cosmaxe", {
	durability: 999999999,
	level: 9, efficiency: 9,
	damage: 10, enchantability: 14
});
ToolAPI.setTool(ItemID.cosmAxe, "cosmaxe", ToolType.axe);
Item.setToolRender(ItemID.cosmAxe, true);

function SetCarr(tool, tool2){
	let item = Player.getCarriedItem();
	let player = Player.get();
	if(item.id==tool && Entity.getSneaking(player)){
		Player.setCarriedItem(item.id, item.count - 1, 0);
		Player.setCarriedItem(tool2, 1, 0);
	}else if(item.id==tool2 && Entity.getSneaking(player)){
		Player.setCarriedItem(item.id, item.count - 1, 0);
		Player.setCarriedItem(tool, 1, 0);
	}
}

Callback.addCallback("ItemUse", function(coords, item, block) {
	if (item.id == ItemID.cosmAxe && Entity.getSneaking(Player.get())) {
		for (var xco = coords.x - 13; xco < coords.x + 13; xco++) {
			for (var zco = coords.z - 13; zco < coords.z + 13; zco++) {
				for (var yco = coords.y - 20; yco < coords.y + 20; yco++) {
					var id = World.getBlockID(xco, yco, zco);
					if (id == 2) World.setBlock(xco, yco, zco, 3);
					else if (AXE_BLOCKS.hasId(id)) World.setBlock(xco, yco, zco, 0);
				}
			}
		}
	}
	SetCarr(ItemID.cosmPickaxe, ItemID.cosmhammer);
	SetCarr(ItemID.cosmShovel, ItemID.cosmdes);
});

IDRegistry.genItemID("cosmHoe");
Item.createItem("cosmHoe", "Hoe of the Green Earth", {
	name: "infhoe", meta: 0
}, { stack: 1 });

ToolAPI.addToolMaterial("cosmhoe", {
	durability: 999999999,
	level: 9, efficiency: 9,
	damage: 6, enchantability: 14
});
ToolAPI.setTool(ItemID.cosmHoe, "cosmhoe", ToolType.hoe);
Item.setToolRender(ItemID.cosmHoe, true);

Callback.addCallback("ItemUse", function(coords, item, block) {
	if (item.id == ItemID.cosmHoe && Entity.getSneaking(Player.get())
		&& (block.id == 2 || block.id == 3) && coords.side == 1) {
			for (var xc = coords.x - 6; xc < coords.x + 6; xc++) {
				for (var zc = coords.z - 6; zc < coords.z + 6; zc++) {
					if (World.getBlockID(xc, coords.y, zc) == 3 || World.getBlockID(xc, coords.y, zc) == 2) {
						World.setBlock(xc, coords.y, zc, 60 /* PLANT_DIRT */);
					}
				}
			}
	}
});


