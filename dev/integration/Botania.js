/*
ModAPI.addAPICallback("BotaniaAPI", function(api){
});*/

var Renderer = {
	setSaplingRender: function(id, x) {
		var shape = new ICRender.CollisionShape();	 
		BlockRenderer.setCustomCollisionShape(Block.getNumericId(id), -1, shape);	
		BlockRenderer.addRenderCallback(id, function(api, coords,block) {
			if (x != 0) {
				for (var i = 0; i < 1/x;i += x) {
					api.renderBoxId(coords.x, coords.y, coords.z, 0 + i, 0.01, 0 + i, x + i, 0.99, x + i, id, block.data);
					api.renderBoxId(coords.x, coords.y, coords.z, (1 - x) - i, 0.01, 0 + i, 1 - i, 0.99, x + i, id, block.data);
				}
			} else {
				api.renderBoxId(coords.x, coords.y, coords.z, 0.4999, 0.01, 0, 0.5, 0.99, 1, id, block.data);
				api.renderBoxId(coords.x, coords.y, coords.z, 0, 0.01, 0.4999, 1, 0.99, 0.5, id, block.data);
			}
		});
		BlockRenderer.enableCustomRender(id);
	}
};

var BlackLight = Particles.registerParticleType({
  texture: "bl",
  size: [20, 20],
  lifetime: [3, 4],
  render: 0,
  velocity: [0, 0.03, 0]
});

IDRegistry.genItemID("tinyPotato");
Item.createItem("tinyPotato", "Tiny Potato", {
	name: "stick" /* tinyPotato */,
	meta: 0
});

IDRegistry.genBlockID("infinitato");
Block.createBlockWithRotation("infinitato", [{
	name: "Infinitato",
	texture: [["infinitato", 2],
			  ["infinitato", 2],
			  ["infinitato", 1],
			  ["infinitato", 0],
			  ["infinitato", 1],
			  ["infinitato", 1]],
	inCreative: true
}]);

Block.setDestroyTime(BlockID.infinitato, 2);
ToolAPI.registerBlockMaterial(BlockID.infinitato, "stone", 2, true);
// Block.setShape(BlockID.infinitato, 0, 0, 0, 0, 24/16, 0);
Block.setShape(BlockID.infinitato, 4/16, 0/16, 4/16, 12/16, 12/16, 12/16);

var renderato = new ICRender.Model();
BlockRenderer.setStaticICRender(BlockID.infinitato, -1, renderato);
var modelato = BlockRenderer.createModel();

modelato.addBox(4.5/16, 0/16, 4.5/16, 11.5/16, 0.5/16, 11.5/16, "infinitato", 2);
modelato.addBox(4.5/16, 11.5/16, 4.5/16, 11.5/16, 12/16, 11.5/16, "infinitato", 2);
modelato.addBox(4/16, 0/16, 4/16, 12/16, 12/16, 4.5/16, "infinitato", 1);
modelato.addBox(4/16, 0/16, 4.5/16, 4.5/16, 12/16, 11.5/16, "infinitato", 0);
modelato.addBox(11.5/16, 0/16, 4.5/16, 12/16, 12/16, 11.5/16, "infinitato", 1);
modelato.addBox(4/16, 0/16, 11.5/16, 12/16, 12/16, 12/16, "infinitato", 1);

renderato.addEntry(modelato);

TileEntity.registerPrototype(BlockID.infinitato, {
	defaultValues: new Object(),
	tick: function() {
		Particles.addParticle(BlackLight, this.x, this.y + 0.2, this.z, 0, 0, 0);
	},
	click: function(id, count, data, coords) {
		Entity.addEffect(Player.get(), 21, 3, 3600, false, false); // 10 health
		Entity.addEffect(Player.get(), 1, 0, 3600, false, false); // Speed
		Entity.addEffect(Player.get(), 3, 1, 3600, false, false); // Haste II
		Entity.addEffect(Player.get(), 5, 1, 3600, false, false); // Strength II
		Entity.addEffect(Player.get(), 22, 0, 3600, false, false); // Absorption
		Entity.addEffect(Player.get(), 23, 0, 3600, false, false); // Saturation
		Entity.addEffect(Player.get(), 8, 0, 3600, false, false); // Jump Boost
		Entity.addEffect(Player.get(), 10, 1, 3600, false, false); // Regeneration II
		Entity.addEffect(Player.get(), 11, 1, 3600, false, false); // Resistance II
		Entity.addEffect(Player.get(), 12, 0, 3600, false, false); // Fire Resistance
		Entity.addEffect(Player.get(), 13, 0, 3600, false, false); // Water Breathing
	}
});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: BlockID.infinitato,
	count: 1
}, [
	"aaaaaaaaa",
	"aaaaaaaaa",
	"aaababaaa",
	"aaaaaaaaa",
	"aabacabaa",
	"aaabbbaaa",
	"aaaaaaaaa",
	"aaaaaaaaa",
	"aaaaaaaaa"
], {
	a: { id:ItemID.tinyPotato },
	b: { id: 264 },
	c: { id: ItemID.catalystInfinity }
});

IDRegistry.genBlockID("gaiaBlock");
Block.createBlock("gaiaBlock", [{
	name: "Gaia block",
	texture: [["block_gaia", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.gaiaBlock, 3);
ToolAPI.registerBlockMaterial(BlockID.gaiaBlock, "stone", 4, true);
	  
var BLOCK_TYPE_HIGHT_LIGHT = Block.createSpecialType({ 
	lightlevel: 15,
	lightopacity: 0
});

IDRegistry.genBlockID("asgWhite");
Block.createBlock("asgWhite", [{
	name: "Asgardandelion",
	texture: [["empty", 0],
			  ["empty", 0],
			  ["asgardandelion", 1]],
	inCreative: false
}], BLOCK_TYPE_HIGHT_LIGHT);
Renderer.setSaplingRender(BlockID.asgWhite, 0);

Block.setDestroyTime(BlockID.asgWhite,0);
Block.registerDropFunction("asgWhite", function(coords, id, data, diggingLevel, toolLevel) {
	return [[ItemID.asgWhite, 1, 0]]; 
});

IDRegistry.genItemID("asgWhite");
Item.createItem("asgWhite", "Asgardandelion", {
	name: "asgardandelion"
});
Item.registerUseFunction("asgWhite", function(coords, item, block) {
    if (block.id == 2) {
        Player.setCarriedItem(item.id, item.count - 1, item.data);
        World.setBlock(coords.x, coords.y + 1, coords.z, BlockID.asgWhite, 0);
    }
});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.asgWhite,
	count: 1
}, [
	"   aaa   ",
	"  aaaaa  ",
	"  aabaa  ",
	"  aaaaa  ",
	"   aaa   ",
	" cc d cc ",
	"ccccdcccc",
	" cc d cc ",
	"	d	"
], {
	a: { id: ItemID.ingotInfinity },
	b: { id: ItemID.catalystInfinity },
	c: { id: ItemID.neutron_nugget },
	d: { id: ItemID.ingotNeutronium }
});

TileEntity.registerPrototype(BlockID.asgWhite, {
	 defaultValues: {
		 curMana: Number.MAX_VALUE,
		 maxMana: Number.MAX_VALUE
	 },
	 tick: function() {
		 // TODO
		 if (this.curMana < Number.MAX_VALUE) {
			 this.curMana = Number.MAX_VALUE;
		 }
	 }
});

IDRegistry.genBlockID("solWhite");
Block.createBlock("solWhite", [{
	name: "Soarleander",
	texture: [["empty", 0],
			  ["empty", 0],
			  ["soarleander", 1]],
	inCreative: false
}]);
Renderer.setSaplingRender(BlockID.solWhite, 0);

Block.setDestroyTime(BlockID.solWhite, 0);
Block.registerDropFunction("solWhite", function(coords, id, data, diggingLevel, toolLevel) {
	 return [[ItemID.solWhite, 1, 0]]; 
});

IDRegistry.genItemID("solWhite");
Item.createItem("solWhite", "Soarleander", {
	name: "soarleander"
});
Item.registerUseFunction("solWhite", function(coords, item, block) {
	if (block.id == 2) {
		Player.setCarriedItem(item.id, item.count - 1, item.data);
		World.setBlock(coords.x, coords.y + 1, coords.z, BlockID.solWhite, 0);
	}
});
