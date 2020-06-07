/*
BUILD INFO:
  dir: dev
  target: main.js
  files: 18
*/



// file: header.js

/*
 ▄▄▄          ██▒   █▓    ▄▄▄          ██▀███      ██▓   ▄▄▄█████▓    ██▓    ▄▄▄      
▒████▄       ▓██░   █▒   ▒████▄       ▓██ ▒ ██▒   ▓██▒   ▓  ██▒ ▓▒   ▓██▒   ▒████▄    
▒██  ▀█▄      ▓██  █▒░   ▒██  ▀█▄     ▓██ ░▄█ ▒   ▒██▒   ▒ ▓██░ ▒░   ▒██▒   ▒██  ▀█▄  
░██▄▄▄▄██      ▒██ █░░   ░██▄▄▄▄██    ▒██▀▀█▄     ░██░   ░ ▓██▓ ░    ░██░   ░██▄▄▄▄██ 
 ▓█   ▓██▒      ▒▀█░      ▓█   ▓██▒   ░██▓ ▒██▒   ░██░     ▒██▒ ░    ░██░    ▓█   ▓██▒
 ▒▒   ▓▒█░      ░ ▐░      ▒▒   ▓▒█░   ░ ▒▓ ░▒▓░   ░▓       ▒ ░░      ░▓      ▒▒   ▓▒█░
  ▒   ▒▒ ░      ░ ░░       ▒   ▒▒ ░     ░▒ ░ ▒░    ▒ ░       ░        ▒ ░     ▒   ▒▒ ░
  ░   ▒           ░░       ░   ▒        ░░   ░     ▒ ░     ░          ▒ ░     ░   ▒   
      ░  ░         ░           ░  ░      ░         ░                  ░           ░  ░
                  ░                                                                   
*/

IMPORT("RecipeTileEntityLib");
IMPORT("ToolType");
IMPORT("SoundAPI");
IMPORT("Bow");

var isHorizon = getCoreAPILevel() > 8;

Item.setRequiresIconOverride = ModAPI.requireGlobal("Item.setRequiresIconOverride");
if (!isHorizon) Game.getGameMode = ModAPI.requireGlobal("Level.getGameMode");
var EntityDataRegistry = ModAPI.requireGlobal("EntityDataRegistry");

var ArmorTick = {
	attachTo: function(arg) {
		if (!arg.tick || arg.id == undefined || arg.type == undefined) return;
		Callback.addCallback("tick", function() {
			if (Player.getArmorSlot(arg.type).id == arg.id) arg.tick();
		});
	}
};

var SaplingRenderer = {
	setOnBlock: function(id, offset) {
		var shape = new ICRender.CollisionShape();
		BlockRenderer.setCustomCollisionShape(Block.getNumericId(id), -1, shape);
		BlockRenderer.addRenderCallback(id, function(api, coords, block) {
			if (offset != undefined && offset != 0) {
				for (var i = 0; i < 1 / offset; i += offset) {
					api.renderBoxId(coords.x, coords.y, coords.z, 0 + i, 0.01, 0 + i, offset + i, 0.99, x + i, id, block.data);
					api.renderBoxId(coords.x, coords.y, coords.z, (1 - offset) - i, 0.01, 0 + i, 1 - i, 0.99, offset + i, id, block.data);
				}
			} else {
				api.renderBoxId(coords.x, coords.y, coords.z, 0.4999, 0.01, 0, 0.5, 0.99, 1, id, block.data);
				api.renderBoxId(coords.x, coords.y, coords.z, 0, 0.01, 0.4999, 1, 0.99, 0.5, id, block.data);
			}
		});
		BlockRenderer.enableCustomRender(id);
	}
};

function makeReplaceable(item, id, replacement) {
	if (item == undefined || typeof item != "object" || typeof item.id != "number") return;
	if (item.id == id) Player.setCarriedItem(replacement, item.count, item.data, item.extra);
	else if (item.id == replacement) Player.setCarriedItem(id, item.count, item.data, item.extra);
}

function addNamedCallback(action, current) {
	Callback.addCallback(current, function() {
		var arguments = Array.prototype.slice.call(arguments);
		(arguments.unshift(current), action.apply(Callback, arguments));
	});
}

function makeSimplifiedCallback(action) {
	var arguments = Array.prototype.slice.call(arguments);
	if (arguments.length == 0) return Logger.Log("can't create makeSimplifiedCallback without action & names", "ERROR");
	if (arguments.length == 1) return Logger.Log("no names specified for makeSimplifiedCallback", "ERROR");
	arguments.shift(); // removing action from callback names, it attached into function
	arguments.forEach(function(current) { addNamedCallback(action, current); });
}




// file: translation.js

Translation.addTranslation("Infinity chunck", {ru: "Кусочек бескоечности"});
Translation.addTranslation("Infinity ingot", {ru: "Слиток бесконечности"});
Translation.addTranslation("Infinity catalyst", {ru: "Катализатор бескоечности"});
Translation.addTranslation("Neutronium ingot", {ru: "Нейтронный слиток"});

Translation.addTranslation("Fractured ore", {ru: "Растрескавшаяся руда"});
Translation.addTranslation("Matrix ingot", {ru: "Слиток кристаллической матрицы"});
Translation.addTranslation("Diamond lattice", {ru: "Алмазная решетка"});
Translation.addTranslation("Ghyper Matter", {ru: "Большой хранитель"});
Translation.addTranslation("Endest pearl", {ru: "Эндест-жемчуг"});
Translation.addTranslation("Gaia block", {ru: "Блок гаи"});
Translation.addTranslation("Infinity block", {ru: "Блок бесконечности"});
Translation.addTranslation("Neutronium block", {ru: "Нейтронный блок"});
Translation.addTranslation("Compresed workbench", {ru: "Сжатый верстак"});
Translation.addTranslation("Double compressed workbench", {ru: "Сильно сжатый верстак"});
Translation.addTranslation("Extrime workbench", {ru: "Экстримальноно сжатый верстак"});
Translation.addTranslation("Compressor neutronium", {ru: "Нейтронный компресор"});
Translation.addTranslation("Neutroinium collector", {ru: "Нейтронный собиратель"});
Translation.addTranslation("Asgardandelion", {ru: "Асгарданделион"});
Translation.addTranslation("Soarleander", {ru: "Соарлеандр"});
Translation.addTranslation("Sword of the Cosmos", {ru: "Меч космоса"});
Translation.addTranslation("World Braker", {ru: "Кирка 'Уничтожитель Миров'"});
Translation.addTranslation("Planet Eater", {ru: "Лопата 'Пожиратель Миров'"});
Translation.addTranslation("Nature's Ruin", {ru: "Торпор 'Руины Природы'"});
Translation.addTranslation("Hoe of the Green Earth", {ru: "Мотыга 'Зеленая Земля'"});
Translation.addTranslation("Cosmos soup", {ru: "Космическая тушенка"});
Translation.addTranslation("Cosmos meatballs", {ru: "Космческие фрикадельки'"});

Translation.addTranslation("Matter Cluster", { de: "Materienansammlung", ru: "Кусок материи", zh: "物质团" });
Translation.addTranslation("Critical Matter Cluster", { de: "Kritische Materienansammlung", ru: "Предельный кусок материи", zh: "临界物质团" });




// file: blocks/resource.js

IDRegistry.genBlockID("gaiaBlock");
Block.createBlock("gaiaBlock", [{
	name: "Gaia block",
	texture: [["block_gaia", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.gaiaBlock, 3);
ToolAPI.registerBlockMaterial(BlockID.gaiaBlock, "stone", 4, true);

IDRegistry.genBlockID("infBlock");
Block.createBlock("infBlock", [{
	name: "Infinity block",
	texture: [["infinity", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.infBlock, 3);
ToolAPI.registerBlockMaterial(BlockID.infBlock, "stone", 4, true);

IDRegistry.genBlockID("neutroniumBlock");
Block.createBlock("neutroniumBlock", [{
	name: "Neutronium block",
	texture: [["neutronium", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.neutroniumBlock, 3);
ToolAPI.registerBlockMaterial(BlockID.neutroniumBlock, "stone", 4, true);

IDRegistry.genBlockID("crystal_matrixAV");
Block.createBlockWithRotation("crystal_matrixAV", [{
	name: "Crystal matrix",
	texture: [["crystal_matrix", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.crystal_matrixAV, 4);
ToolAPI.registerBlockMaterial(BlockID.crystal_matrix, "stone", 4, true);




// file: blocks/sapling.js

IDRegistry.genBlockID("asgWhite");
Block.createBlock("asgWhite", [{
	name: "Asgardandelion",
	texture: [["empty", 0], ["empty", 0],
		["asgardandelion", 1]],
	inCreative: false
}]);

IDRegistry.genItemID("asgWhite");
Item.createItem("asgWhite", "Asgardandelion", {
	name: "asgardandelion"
});
SaplingRenderer.setOnBlock(BlockID.asgWhite, 0);

Item.registerUseFunction("asgWhite", function(coords, item, block) {
	var place = coords.relative;
	if (GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))) {
		World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
		Player.setCarriedItem(item.id, item.count - 1, item.data);
	}
});

Callback.addCallback("GenerateChunk", function(x, z) {
	for (var i = 0; i < 1; i++) {
		coords = GenerationUtils.randomCoords(x, z, 20, 96);
		for (var k = 20; k < 96; k++) {
			if (World.getBlockID(coords.x, k, coords.z) == 3) {
				if (World.getBlockID(coords.x, k + 1, coords.z) != 0) return;
				World.setBlock(coords.x, k + 1, coords.z, BlockID.asgWhite, 0);
			}
		}
	}
});

IDRegistry.genBlockID("solWhite");
Block.createBlock("solWhite", [{
	name: "Soarleander",
	texture: [["empty", 0], ["empty", 0],
		["soarleander", 1]],
	inCreative: false
}]);

IDRegistry.genItemID("solWhite");
Item.createItem("solWhite", "Soarleander", {
	name: "soarleander"
});
SaplingRenderer.setOnBlock(BlockID.solWhite, 0);

Item.registerUseFunction("solWhite", function(coords, item, block) {
	var place = coords.relative;
	if (GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))) {
		World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
		Player.setCarriedItem(item.id, item.count - 1, item.data);
	}
});

Callback.addCallback("GenerateChunk", function(x, z) {
	for (var i = 0; i < 1; i++) {
		coords = GenerationUtils.randomCoords(x, z, 20, 96);
		for (var k = 20; k < 96; k++) {
			if (World.getBlockID(coords.x, k, coords.z) == 3) {
				if (World.getBlockID(coords.x, k + 1, coords.z) != 0) return;
				World.setBlock(coords.x, k + 1, coords.z, BlockID.solWhite, 0);
			}
		}
	}
});




// file: blocks/machine.js

IDRegistry.genBlockID("compressorAv");
Block.createBlockWithRotation("compressorAv", [{
	name: "Compressor neutronium",
	texture: [["side", 0], ["top_comp", 0], ["side", 0],
				["compfront", 0], ["side", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.compressorAv, 4);
ToolAPI.registerBlockMaterial(BlockID.compressorAv, "stone", 4, true);

IDRegistry.genBlockID("neutCo"); 
Block.createBlockWithRotation("neutCo", [{
	name: "Neutroinium collector",
	texture: [["side", 0], ["top", 0], ["side", 0],
				["active", 0], ["side", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.neutCo, 4);
ToolAPI.registerBlockMaterial(BlockID.neutCo, "stone", 4, true);




// file: blocks/workbench.js

IDRegistry.genBlockID("compreBlock");
Block.createBlock("compreBlock", [{
	name: "Compresed workbench",
	texture: [["compressed", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.compreBlock, 4);
ToolAPI.registerBlockMaterial(BlockID.compreBlock, "wood", 0, true);

IDRegistry.genBlockID("dcompreBlock");
Block.createBlock("dcompreBlock", [{
	name: "Double compressed workbench",
	texture: [["double_compressed", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.dcompreBlock, 6);
ToolAPI.registerBlockMaterial(BlockID.dcompreBlock, "wood", 2, true);

IDRegistry.genBlockID("extWorckbench");
Block.createBlockWithRotation("extWorckbench", [{
	name: "Extrеme workbench",
	texture: [["crafting", 2], ["craftingtop", 0],
					["craftingside", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.extWorckbench, 4);
ToolAPI.registerBlockMaterial(BlockID.extWorckbench, "stone", 3, true);




// file: items/define.js

IDRegistry.genItemID("crystal_matrix_ingot");
Item.createItem("crystal_matrix_ingot", "Crystal matrix ingot", {
	name: "crystal_matrix_ingot", meta: 0
});

IDRegistry.genItemID("diamond_lattice");
Item.createItem("diamond_lattice", "Diamond lattice", {
	name: "diamond_lattice", meta: 0
});

IDRegistry.genItemID("neutron_pile");
Item.createItem("neutron_pile", "Neutronium pile", {
	name: "neutron_pile", meta: 0
});

IDRegistry.genItemID("neutron_nugget");
Item.createItem("neutron_nugget", "Neutronium nugget", {
	name: "neutron_nugget", meta: 0
});

IDRegistry.genItemID("ingotNeutronium");
Item.createItem("ingotNeutronium", "Neutronium ingot", {
	name: "neutronium_ingot", meta: 0
});

IDRegistry.genItemID("catalystInfinity");
Item.createItem("catalystInfinity", "Infinity catalyst", {
	name: "infinity_catalyst", meta: 0
});

IDRegistry.genItemID("ingotInfinity");
Item.createItem("ingotInfinity", "Infinity ingot", {
	name: "infinity_ingot", meta: 0
});

IDRegistry.genItemID("record_fragment");
Item.createItem("record_fragment", "Record Fragment", {
	name: "record_fragment", meta: 0
});

IDRegistry.genItemID("ironsing");
Item.createItem("ironsing", "Iron Singularity", {
	name: "singularity_iron", meta: 0
});

IDRegistry.genItemID("goldsing");
Item.createItem("goldsing", "Gold Singularity", {
	name: "singularity_gold", meta: 0
});

IDRegistry.genItemID("lapissing");
Item.createItem("lapissing", "Lapis Singularity", {
	name: "singularity_lapis", meta: 0
});

IDRegistry.genItemID("redstonesing");
Item.createItem("redstonesing", "Redstone Singularity", {
	name: "singularity_redstone", meta: 0
});

IDRegistry.genItemID("quartzsing");
Item.createItem("quartzsing", "Quartz Singularity", {
	name: "singularity_quartz", meta: 0
});

IDRegistry.genItemID("oreFractured");
Item.createItem("oreFractured", "Fractured ore", {
	name: "fractured_ore", meta: 0
});

IDRegistry.genItemID("infChunck");
Item.createItem("infChunck", "Infinity chunck", {
	name: "resource_infinity_drop", meta: 0
});

IDRegistry.genItemID("gypMatter");
Item.createItem("gypMatter", "Ghyper Matter", {
	name: "gypmatter", meta: 0
});

IDRegistry.genItemID("endestPearl");
Item.createThrowableItem("endestPearl", "Endest pearl", {
	name: "endest", meta: 0
}, { stack: 16 });




// file: items/food.js

IDRegistry.genItemID("ultimstew");
Item.createFoodItem("ultimstew", "Cosmos soup", {
	name: "ultimstew", meta: 0,
}, { isTech: false, stack: 64, food: 10 });

Callback.addCallback("FoodEaten", function(heal, satRatio) {
	if (Player.getCarriedItem().id == ItemID.ultimstew) {
		Entity.addEffect(Player.get(), 12, 1, 12000, false, false);
		Entity.addEffect(Player.get(), 5, 1, 12000, false, false);
		Entity.addEffect(Player.get(), 6, 1, 30, false, false);
		Entity.addEffect(Player.get(), 21, 2, 12000, false, false);
		Entity.addEffect(Player.get(), 22, 2, 12000, false, false);
	}
});

IDRegistry.genItemID("cosmMeatballs");
Item.createFoodItem("cosmMeatballs", "Cosmos meatballs", {
	name: "cosm_meatballs", meta: 0,
}, { isTech: false, stack: 64, food: 10 });

Callback.addCallback("FoodEaten", function(heal, satRatio) {
	if (Player.getCarriedItem().id == ItemID.cosmMeatballs) {
		Entity.addEffect(Player.get(), 12, 1, 12000, false, false);
		Entity.addEffect(Player.get(), 1, 2, 12000, false, false);
		Entity.addEffect(Player.get(), 5, 1, 12000, false, false);
		Entity.addEffect(Player.get(), 6, 1, 30, false, false);
		Entity.addEffect(Player.get(), 21, 2, 12000, false, false);
		Entity.addEffect(Player.get(), 22, 2, 12000, false, false);
	}
});




// file: items/tool.js

function addUsageChangeCallback(id, replacement) {
	makeSimplifiedCallback(function(name, item) {
		if (name == "ItemUse" && !Entity.getSneaking(Player.get())) return;
		makeReplaceable(item, id, replacement);
	}, "ItemUse", "ItemUseNoTarget");
}

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
Item.createItem("skull_sword", "Skull sword", {
	name: "skull_sword", meta: 0
}, { stack: 1 });
Item.setEnchantType("skull_sword", 14, 25);

ToolAPI.addToolMaterial("skull_sword", {
	durability: 999999999,
	level: 7, efficiency: 6,
	damage: 999, enchantability: 14
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

IDRegistry.genItemID("infbow");
Item.createItem("infbow", "Inf Bow", {
	name: "bow_idle", meta: 0
}, { stack: 1 });
Item.describeItem(ItemID.infbow, {
	toolRender: true,
	maxDamage: 999999999,
	useAnimation: 4
});

ToolAPI.addToolMaterial("cosmbow", {
	durability: 999999999,
	level: 9, efficiency: 9,
	damage: 1, enchantability: 14
});
ToolAPI.setTool(ItemID.infbow, "cosmbow", ToolType.sword);
Item.setToolRender(ItemID.infbow, true);

var infbow = new Bow();
infbow.set({
	id: ItemID.infbow,
	texture: "bow_pull",
	bullets: [ItemID.infbow],
	skin: "mob/heavenarrow.png",
	speed: 10, damage: 60, variations: 3
});

var INFINITE_BOW_ARROW_SHOT_COUNT = 14;

Callback.addCallback("ProjectileHit", function(projectile, item, target, coords) {
	if (projectile.id == ItemID.infbow && item.id == ItemID.infbow) {
		for (var i = 0; i < INFINITE_BOW_ARROW_SHOT_COUNT; i++) {
			Entity.spawn(coords.x + 0.5, coords.y + 3, coords.z + 1.5, 80, [heavenarrow]);
		}
	}
});

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

addUsageChangeCallback(ItemID.cosmPickaxe, ItemID.cosmhammer);

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

addUsageChangeCallback(ItemID.cosmShovel, ItemID.cosmdes);

var DESTROYER_BLOCKS = [14, 15, 16, 21, 56, 73, 129, 49, 7];
DESTROYER_BLOCKS.hasId = function(id) {
	return this.indexOf(id) != -1;
};

Callback.addCallback("DestroyBlock", function(coords, block, player) {
	if (Player.getCarriedItem().id == ItemID.cosmdes) {
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

var AXE_BLOCKS = [17, 18, 31, 38];
AXE_BLOCKS.hasId = function(id) {
	return this.indexOf(id) != -1;
};

Callback.addCallback("ItemUse", function(coords, item, block) {
	if (item.id == ItemID.cosmAxe && Entity.getSneaking(Player.get())) {
		for (var xco = coords.x - 13; xco < coords.x + 13; xco++) {
			for (var zco = coords.z - 13; zco < coords.z + 13; zco++) {
				for (var yco = coords.y - 20; yco < coords.y + 20; yco++) {
					var id = World.getBlockID(xco, yco, zco);
					if (id == 2 /* DIRT */) World.setBlock(xco, yco, zco, 3);
					else if (AXE_BLOCKS.hasId(id)) World.setBlock(xco, yco, zco, 0);
				}
			}
		}
	}
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




// file: items/armor.js

IDRegistry.genItemID("inf_helmet");
Item.createArmorItem("inf_helmet", "Infinity Helmet", {
	name: "helmetAV", meta: 0
}, {
	isTech: false, armor: 3, type: "helmet",
	texture: "armor/infinity_armor_full0.png",
	durability: 999999999
});
ArmorTick.attachTo({
	id: ItemID.inf_helmet,
	type: 0,
	tick: function() {
		Entity.addEffect(Player.get(), 16, 1, 19, false, false);
		Entity.addEffect(Player.get(), 23, 190, 19, false, false);
		Entity.addEffect(Player.get(), 13, 190, 19, false, false);
	}
});

IDRegistry.genItemID("inf_chestplate");
Item.createArmorItem("inf_chestplate", "Infinity Chestplate", {
	name: "chestaplateAV", meta: 0
}, {
	isTech: false, armor: 8, type: "chestplate",
	texture: "armor/infinity_armor_full0.png",
	durability: 999999999
});
ArmorTick.attachTo({
	id: ItemID.inf_chestplate,
	type: 1,
	tick: function() {
		Entity.addEffect(Player.get(), 11, 190, 19, false, false);
		Entity.addEffect(Player.get(), 10, 190, 19, false, false);
		Player.setFlyingEnabled(true);
	}
});

Callback.addCallback("tick", function() {
	if (Player.getArmorSlot(1).id ==! ItemID.inf_chestplate && !Game.getGameMode()) {
		Player.setFlyingEnabled(false);
	}
});

Callback.addCallback("tick", function() {
	if (Player.getArmorSlot(1).id == ItemID.inf_chestplate && Player.getFlying(true)) {
		wing.load();
    } else if (Player.getArmorSlot(1).id == ItemID.inf_chestplate || Player.getFlying(false)) {
		wing.destroy();
	}
});

IDRegistry.genItemID("inf_leggings");
Item.createArmorItem("inf_leggings", "Infinity Leggings", {
	name: "legginsAV", meta: 0
}, {
	isTech: false, armor: 6, type: "leggings",
	texture: "armor/infinity_armor_full1.png",
	durability: 999999999
});
ArmorTick.attachTo({
	id: ItemID.inf_leggings,
	type: 2,
	tick: function() {
		Entity.addEffect(Player.get(), 1, 9, 19, false, false);
	}
});

IDRegistry.genItemID("inf_boots");
Item.createArmorItem("inf_boots", "Infinity Boots", {
	name: "bootAV", meta: 0
}, {
	isTech: false, armor: 3, type: "boots",
	texture: "armor/infinity_armor_full0.png",
	durability: 999999999
});
ArmorTick.attachTo({
	id: ItemID.inf_boots,
	type: 3,
	tick: function() {
		Entity.addEffect(Player.get(), 8, 3, 19, false, false);
	}
});




// file: items/animation.js

var fiveBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.ironsing, function(item, texture) {
	return { name: "singularity_iron", meta: fiveBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.ironsing, true);

Item.registerIconOverrideFunction(ItemID.goldsing, function(item, texture) {
	return { name: "singularity_gold", meta: fiveBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.goldsing, true);

Item.registerIconOverrideFunction(ItemID.lapissing, function(item, texture) {
	return { name: "singularity_lapis", meta: fiveBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.lapissing, true);

Item.registerIconOverrideFunction(ItemID.quartzsing, function(item, texture) {
	return { name: "singularity_quartz", meta: fiveBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.quartzsing, true);

Item.registerIconOverrideFunction(ItemID.redstonesing, function(item, texture) {
	return { name: "singularity_redstone", meta: fiveBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.redstonesing, true);

var fourBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.endestPearl, function(item, texture) {
	return { name: "endest", meta: fourBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.endestPearl, true);

var sevenBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.cosmMeatballs, function(item, texture) {
	return { name: "cosm_meatballs", meta: sevenBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.cosmMeatballs, true);

var eigthBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.inf_chestplate, function(item, texture) {
	return { name: "chestaplateAV", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.inf_chestplate, true);

Item.registerIconOverrideFunction(ItemID.cosmhammer, function(item, texture) {
	return { name: "infhammer", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.cosmhammer, true);

Item.registerIconOverrideFunction(ItemID.cosmHoe, function(item, texture) {
	return { name: "infhoe", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.cosmHoe, true);

Item.registerIconOverrideFunction(ItemID.catalystInfinity, function(item, texture) {
	return { name: "infinity_catalyst", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.catalystInfinity, true);

Item.registerIconOverrideFunction(ItemID.ingotInfinity, function(item, texture) {
	return { name: "infinity_ingot", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.ingotInfinity, true);

Item.registerIconOverrideFunction(ItemID.cosmPickaxe, function(item, texture) {
	return { name: "infpickaxe", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.cosmPickaxe, true);

Item.registerIconOverrideFunction(ItemID.cosmShovel, function(item, texture) {
	return { name: "infshovel", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.cosmShovel, true);

Item.registerIconOverrideFunction(ItemID.cosmSword, function(item, texture) {
	return { name: "cosm_sword", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.cosmSword, true);

Item.registerIconOverrideFunction(ItemID.inf_leggings, function(item, texture) {
	return { name: "legginsAV", meta: eigthBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.inf_leggings, true);

var threeBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.skull_sword, function(item, texture) {
	return { name: "skull_sword", meta: threeBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.skull_sword, true);

Item.registerIconOverrideFunction(ItemID.orb_armok, function(item, texture){
	return { name: "orb_armok", meta: threeBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.orb_armok, true);

var twoBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.ingotNeutronium, function(item, texture){
	return { name: "neutronium_ingot", meta: twoBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.ingotNeutronium, true);

var longestBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.ultimstew, function(item, texture){
	return { name: "ultimstew", meta: longestBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.ultimstew, true);

Callback.addCallback("tick", function(){
	var time = World.getThreadTime() % 4;
	if (time == 0 || time == 4) {
		if (twoBlinkIndex < 2) twoBlinkIndex++;
		else twoBlinkIndex = 0;
		if (threeBlinkIndex < 3) threeBlinkIndex++;
		else threeBlinkIndex = 0;
		if (fourBlinkIndex < 4) fourBlinkIndex++;
		else fourBlinkIndex = 0;
		if (fiveBlinkIndex < 5) fiveBlinkIndex++;
		else fiveBlinkIndex = 0;
		if (sevenBlinkIndex < 7) sevenBlinkIndex++;		
		else sevenBlinkIndex = 0;
		if (eigthBlinkIndex < 8) eigthBlinkIndex++;
		else eigthBlinkIndex = 0;
		if (longestBlinkIndex < 27) longestBlinkIndex++;
		else longestBlinkIndex = 0;
	}
});

var render = new Render(); 
render.setPart("head", [{
	type: "box", uv: { x: 0, y: 0 },
	coords: { x: 0, y: 31, z: 0 },
	size: { x: 64, y: 64, z: 0 }
}], {});

var coords = Player.getPosition();
var wing = new Animation.Base(coords.x, coords.y + 1, coords.z);
if (isHorizon) wing.setInterpolationEnabled(true);
wing.describe({ render: render.getId(),
	skin: "armor/infinity_armor_wing_full.png" });

wing.loadCustom(function() {
	var coords = Player.getPosition();
	this.setPos(coords.x, coords.y + 1, coords.z); 
    this.refresh();
});




// file: gui/table.js

var guiCompressedTable = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Compressed crafting table"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [],
	elements: {}
});

TileEntity.registerPrototype(BlockID.compreBlock, {
	getGuiScreen: function() {
		return guiCompressedTable;
	}
});




// file: gui/double_table.js

var guiDouble = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Double compressed crafting table"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [],
	elements: {}
});

TileEntity.registerPrototype(BlockID.dcompreBlock, {
	getGuiScreen: function() {
		return guiDouble;
	}
});




// file: gui/extreme_table.js

var guiExtremeTable = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Extreme crafting table"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [],
	elements: {
		scale_0: {
			type: "scale",
			x: 843, y: 252,
			direction: 0,
			bitmap: "arrow",
			scale: 3.2,
			value: 1
		},
		guid: {
			type: "button",
			x: 920, y: 50,
			scale: 1.9,
			bitmap: "guid",
			bitmap2: "guid_press",
			clicker: {
				onClick: function() {
					Game.tipMessage("Isn't worked");
				}
			}
		},
		outputSlot: {
			type: "slot",
			x: 930, y: 250,
			size: 50
		}
	}
});

/* let slot = this.container.getSlot("slot");
if(slot.id == 266) return true */

var content = guiExtremeTable.getContent();
var row = 0, x = 380, y = 50;
for (var i = 0; i < 81; i++) {
	content.elements["inputSlot" + i] = {
		type: "slot",
		x: x, y: y,
		size: 50
	};
	x += 50;
	row++;
	if (row >= 9) {
		x = 380;
		y += 50;
		row = 0;
	}
}

RecipeTE.registerWorkbench("extWorckbench", {
	GuiScreen: guiExtremeTable,
	rows: 9, cols: 9
});




// file: gui/compressor.js

var guiCompressor = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Compressor"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [{
		type:"bitmap",
		x: 574, y: 238,
		scale: 3.6,
		bitmap: "arrow"
	}],
	elements: {
		slot_0: {
			type: "slot",
			x: 500, y: 230,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		progress: {
			type: "scale",
			x: 574, y: 238,
			direction: 0,
			bitmap: "progress",
			scale: 3.6,
			value: 1
		},
		sing: {
			type: "scale",
			x: 657, y: 228,
			direction: 0,
			bitmap: "sing",
			scale: 2.3,
			value: 1
		},
		slot_1: {
			type: "slot",
			x: 740, y: 230,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		count: {
			type: "text",
			x: 580, y: 310,
			width: 120,
			height: 16,
			text: "0/0"
		},
		block: {
			type: "text",
			x: 580, y: 360,
			width: 120,
			height: 16,
			text: ""
		}
	}
});

var ModRecipe = {
	recipe: {},
	addRecipe:function(id, recipe){
		this.recipe[id] = recipe;
	},
	getRecipe: function(id) {
		return this.recipe[id] || null;
	}
};

ModRecipe.addRecipe(42, {
	count: 850,
	out: ItemID.ironsing,
	name: "Iron"
});
ModRecipe.addRecipe(41, {
	count: 650,
	out: ItemID.goldsing,
	name: "Gold"
});
ModRecipe.addRecipe(155, {
	count: 750,
	out: ItemID.quartzsing,
	name: "Quartz"
});
ModRecipe.addRecipe(152, {
	count: 950,
	out: ItemID.redstonesing,
	name: "Redstone"
});
ModRecipe.addRecipe(22, {
	count: 850,
	out: ItemID.lapissing,
	name: "Lapis"
});

TileEntity.registerPrototype(BlockID.compressorAv, {
	defaultValues:{
		count: 0, end: 0,
		progress: 0,
		id: 0, block: ""
	},
	getGuiScreen: function() {
		return guiCompressor;
	},
	tick: function() {
		var input = this.container.getSlot("input");
		var output = this.container.getSlot("output");
		var recipe = ModRecipe.getRecipe(input.id);
		if (recipe) {
			if (this.data.id == 0 && input.id != 0) {
				this.data.id = input.id;
			}
			if (input.id == this.data.id && input.id != 0) {
				this.data.block = recipe.name;
				this.data.count += 1;
				this.data.end = recipe.count; 
				this.data.progress += 1 / this.data.end;
				input.count--;
			}
			if(this.data.progress >= 1) {
				output.id = recipe.out;
				output.count++;
				this.data.count -= this.data.end;
				this.data.id = 0;
				this.data.progress = 0;
			}
		}
		this.container.setText("block", "Block of " + this.data.block);
		this.container.setText("count", this.data.count + " / " + this.data.end);
		this.container.setScale("progress", this.data.progress);
		this.container.validateAll();
	}
});




// file: gui/collector.js

var guiCollector = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Collector"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [],
	elements: {
		output: {
			type: "slot",
			x: 570, y: 160,
			size: 102
		},
		progress: {
			type: "text",
			x: 545, y: 290,
			width: 120,
			height: 16,
			text: "Progress: 0%"
		}
	}
});

var COLLECTOR_MAX = 6000;

TileEntity.registerPrototype(BlockID.neutCo, {
	defaultValues: { progress: 0 },
	getGuiScreen: function() {
		return guiCollector;
	},
	tick: function() {
		slot = this.container.getSlot("output");
		this.container.setText("progress", "Progress: " + parseInt(this.data.progress / COLLECTOR_MAX * 100) + "%");
		if (++this.data.progress >= COLLECTOR_MAX && slot.count < 64) {
			if (slot.id == ItemID.neutron_pile) {
				slot.count ++;
				this.data.progress = 0;
			} else if (slot.id == 0 && !slot.count) {
				slot.id = ItemID.neutron_pile;
			} else this.data.progress = COLLECTOR_MAX;
		}
	}
});




// file: items/crafts.js

Callback.addCallback("LevelLoaded", function() {
	Recipes.addShaped({
		id: ItemID.diamond_lattice,
		count: 1, data: 0
	}, ["aoa",
		"oao",
		"aoa"], ["a", 264, 0]);
	Recipes.addShaped({
		id: BlockID.neutroniumBlock,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.ingotNeutronium, 0]);
	Recipes.addShaped({
		id: ItemID.neutron_nugget,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.neutron_pile, 0]);
	Recipes.addShaped({
		id: ItemID.ingotNeutronium,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.neutron_nugget, 0]);
	Recipes.addShaped({
		id: ItemID.crystal_matrix_ingot,
		count: 1, data: 0
	}, ["aba",
		"aba",
		"ooo"], ["a", ItemID.diamond_lattice, 0, "b", 399, 0]);
	Recipes.addShaped({
		id: BlockID.compreBlock,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", 58, 0]);
	Recipes.addShaped({
		id: BlockID.dcompreBlock,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", BlockID.compreBlock, 0]);
	Recipes.addShaped({
		id: BlockID.crystal_matrixAV,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.crystal_matrix_ingot, 0]);
	Recipes.addShaped({
		id: BlockID.extWorckbench,
		count: 1, data: 0
	}, ["aaa",
		"aba",
		"aaa"], ["a", ItemID.crystal_matrix_ingot, 0, "b", BlockID.dcompreBlock, 0]);
});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: BlockID.neutCo,
	count: 1
}, ["aabbbbbaa",
	"a bbbbb a",
	"a  ccc  a",
	"d ccccc d",
	"a ccdcc a",
	"d ccccc d",
	"a  ccc  a",
	"a       a",
	"aaadadaaa"], {
		a: { id: 42 }, b: { id: 155 }, c: { id: 152 },
		d:{ id:ItemID.crystal_matrix_ingot }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.endestPearl,
	count: 1
}, ["   aaa   ",
	" aabbbaa ",
	" abbbbba ",
	"abbbcbbba",
	"abbcscbba",
	"abbbcbbba",
	" abbbbba ",
	" aabbbaa ",
	"   aaa   "], {
		a: { id: 121 }, b: { id: 368 },
		c: { id:ItemID.ingotNeutronium },
		s: { id:399 }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: BlockID.compressorAv,
	count: 1
}, ["aaabbbaaa",
	"c n   n c",
	"a n   n a",
	"c n   n c",
	"rnn g nnr",
	"c n   n c",
	"a n   n a",
	"c n   n c",
	"aaacacaaa"], {
		a: { id: 42 }, b: { id: 410 },
		c: { id: ItemID.crystal_matrix_ingot },
		n: { id: ItemID.ingotNeutronium },
		r: { id: 152 },
		g: { id: BlockID.neutroniumBlock }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmMeatballs,
	count: 2
}, ["abbccddee",
	"ff       ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.neutron_pile },
		b: { id: 363 }, c: { id: 365 },
		d: { id: 319 }, f: { id: 349 },
		e: { id:411 }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.ultimstew,
	count: 1
}, ["abbccddee",
	"ggffii   ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.neutron_pile },
		b: { id: 256 }, c: { id: 391 },
		d: { id: 392 }, e: { id:81 },
		g: { id: 40 }, f: { id: 39 },
		i: { id: 372 }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.catalystInfinity,
	count: 1
}, ["aiglrqube",
	"f        ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "], {
		a: { id: 133 },
		i: { id: ItemID.ironsing },
		g: { id: ItemID.goldsing },
		l: { id: ItemID.lapissing },
		r: { id: ItemID.redstonesing },
		q: { id: ItemID.quartzsing },
		u: { id: ItemID.ultimstew },
		b: { id: ItemID.cosmMeatballs },
		e: { id: ItemID.endestPearl },
		f: { id: ItemID.record_fragment }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.ingotInfinity,
	count: 1
}, ["aaaaaaaaa",
	"abccbccba",
	"acbbcbbca",
	"abccbccba",
	"aaaaaaaaa",
	"         ",
	"         ",
	"         ",
	"         "],{
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.crystal_matrix_ingot },
		c: { id: ItemID.catalystInfinity }
	});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmSword,
	count: 1
}, ["       aa",
	"      aaa",
	"     aaa ",
	"    aaa  ",
	" b aaa   ",
	"  baa    ",
	"  cb     ",
	" c  b    ",
	"d        "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: ItemID.crystal_matrix_ingot },
		c: { id: ItemID.ingotNeutronium },
		d: { id:ItemID.catalystInfinity }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmAxe,
	count: 1
}, [" a       ",
	"aaaaa    ",
	"aaaa     ",
	" ab      ",
	"  b      ",
	"  b      ",
	"  b      ",
	"  b      ",
	"  b      "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmShovel,
	count: 1
}, ["      aaa",
	"     aaba",
	"      aaa",
	"     c  a",
	"    c    ",
	"   c     ",
	"  c      ",
	" c       ",
	"c        "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: BlockID.infBlock },
		c: { id:ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
    id: ItemID.infbow,
    count: 1
}, ["   aa    ",
	"  a b    ",
	" a  b    ",
	"a   b    ",
	"c   b    ",
	"a   b    ",
	" a  b    ",
	"  a b    ",
	"   aa    "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: 35 },
		c: { id: BlockID.crystal_matrixAV }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmPickaxe,
	count: 1
}, [" aaaaaaa ",
	"aaaabaaaa",
	"aa  c  aa",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: BlockID.crystal_matrixAV },
		c: { id:ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmHoe,
	count: 1
}, ["     a   ",
	" bbbbbb  ",
	"bbbbbbb  ",
	"b    bb  ",
	"     a   ",
	"     a   ",
	"     a   ",
	"     a   ",
	"     a   "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.skull_sword,
	count: 1
}, ["       ab",
	"      aba",
	"     aba ",
	"    aba  ",
	" c aba   ",
	"  cba    ",
	"  wc     ",
	" w  c    ",
	"s        "], {
		a: { id: ItemID.crystal_matrix_ingot },
		b: { id:377 }, c: { id:352 },
		w: { id: 17 }, s: { id:399 }
	});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_helmet,
	count: 1
}, ["  aaaaa  ",
	" abbbbba ",
	" a cbc a ",
	" abbbbba ",
	" abbbbba ",
	" ab b ba ",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity },
		c: { id: ItemID.catalystInfinity }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_chestplate,
	count: 1
}, [" aa   aa ",
	"aaa   aaa",
	"aaa   aaa",
	" abbbbba ",
	" abbcbba ",
	" abbbbba ",
	" abbbbba ",
	" abbbbba ",
	"  aaaaa  "], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity },
		c: { id: BlockID.crystal_matrixAV }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_leggings,
	count: 1
}, ["aaaaaaaaa",
	"abbbcbbba",
	"abaacaaba",
	"aba   aba",
	"aea   aea",
	"aba   aba",
	"aba   aba",
	"aba   aba",
	"aba   aba"], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity },
		c: { id: ItemID.catalystInfinity },
		e: { id:BlockID.crystal_matrixAV }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_boots,
	count: 1
}, [" aaa aaa ",
	" aba aba ",
	" aba aba ",
	"aaba abaa",
	"abba abba",
	"aaaa aaaa",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity }
	});




// file: entity/sphere.js

var holerender = new Render();
var holemesh = new RenderMesh(__dir__ + "models/blackhole.obj", "obj", {
	scale: [0.0675, 0.0675, 0.0675], translate: [0, -2.4, 0]
});
holerender.getPart("body").setMesh(holemesh);

var BlackHole = function() {
	this.RENDER = holerender;
	this.SKIN = "mob/black_hole.png";
	this.POOT_RANGE = 0.2;
	this.PARTICLES = Native.ParticleType.portal;
	this.PARTICLE_SPEED = 4.5;
	this.PARTICLE_COUNT = 10;
	
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

BlackHole.prototype.getVoidScale = function() {
	var life = this.age / this.LIFE_TIME, curve;
	if (life < this.COLLAPSE) curve = 0.005 + this.ease(1.0 - (this.COLLAPSE - life) / this.COLLAPSE) * 0.995;
	else curve = this.ease(1.0 - (life - this.COLLAPSE) / (1.0 - this.COLLAPSE));
	return 10.0 * curve;
};

BlackHole.prototype.ease = function(input) {
	var t = (input || 0) - 1.0;
	return Math.sqrt(1.0 - t * t);
};

BlackHole.prototype.isCollapsed = function() {
	return this.age / this.LIFE_TIME >= this.COLLAPSE;
};

BlackHole.prototype.updateScale = function(age) {
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

BlackHole.prototype.spawnParticles = function(range) {
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

BlackHole.prototype.suckAndDamage = function() {
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

BlackHole.prototype.eatBlocks = function() {
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

BlackHole.prototype.initialize = function(ticks) {
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

BlackHole.prototype.suck = function(coords) {
	this.coords = coords, this.initialize();
	for (var i = 0; i < this.SPAWN_PARTICLE_SCALE; i++)
		this.spawnParticles(this.SPAWN_PARTICLE_RADIUS);
	this.sound = new Sound("gapingVoid.ogg");
	this.sound.setInBlock(this.coords, 8);
	this.sound.play();
};

var ITEM_TYPES = [Native.EntityType.ITEM, Native.EntityType.EGG, Native.EntityType.ARROW, Native.EntityType.SMALL_FIREBALL, Native.EntityType.SNOWBALL, Native.EntityType.EXPERIENCE_ORB];
ITEM_TYPES.isItem = function(id) { return this.indexOf(id) != -1; };

function handleBlackHole(coords) {
	new BlackHole().suck(coords);
}

Callback.addCallback("ProjectileHit", function(projectile, item, target, coords) {
	if (Game.getGameMode() != 1) Player.decreaseCarriedItem(1);
	handleBlackHole(Entity.getPosition(projectile));
});




