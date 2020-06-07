/*
ModAPI.addAPICallback("BotaniaAPI", function(api){
});*/

var Renderer={
        setSaplingRender:function(id,x){
        var shape = new ICRender.CollisionShape();     
        BlockRenderer.setCustomCollisionShape(Block.getNumericId(id), -1, shape);    
        BlockRenderer.addRenderCallback(id, function(api, coords,block) {
            if(x!=0){
                for(var i = 0;i < 1/x;i+=x){
                api.renderBoxId(coords.x, coords.y, coords.z,0+i, 0.01, 0+i, x+i, 0.99, x+i,id, block.data);
                api.renderBoxId(coords.x, coords.y, coords.z,(1-x)-i, 0.01, 0+i,1-i, 0.99, x+i,id, block.data);
                }
            }
            else{
                api.renderBoxId(coords.x, coords.y, coords.z, 0.4999, 0.01, 0, 0.5, 0.99, 1,id, block.data);
                api.renderBoxId(coords.x, coords.y, coords.z, 0, 0.01, 0.4999, 1, 0.99, 0.5, id, block.data);
            }
        })
        BlockRenderer.enableCustomRender(id);
    }
};


  //infinitato

IDRegistry.genItemID("tinyPotato");
Item.createItem("tinyPotato", "Tiny Potato", {
	name: "tinyPotato", meta: 0});

IDRegistry.genBlockID("infinitato");
Block.createBlockWithRotation("infinitato", [
	{name: "Infinitato", texture: 
	[["infinitato", 2],
	 ["infinitato", 2],
	 ["infinitato", 1],
	 ["infinitato", 0],
	 ["infinitato", 1],
	 ["infinitato", 1]],
	      inCreative: true}]);

Block.setDestroyTime(BlockID.infinitato,2);
ToolAPI.registerBlockMaterial(BlockID.infinitato, "stone", 2, true);

//Block.setBlockShape(BlockID.infinitato, {x: 0, y: 0, z: 0}, {x: 0, y: 1.5, z: 0});
var infinitatoModel = new ICRender.CollisionShape();
var entry = infinitatoModel.addEntry();
entry.addBox(0/16, 0/16, 0/16, 16/16, 24/16, 16/16);
BlockRenderer.setCustomCollisionShape(BlockID.infinitato, -1, infinitatoModel); 


var render = new ICRender.Model();
BlockRenderer.setStaticICRender(BlockID.infinitato, -1, render);
var model = BlockRenderer.createModel();
model.addBox(1/16, 1/16, 0/16, 16/16, 23/16, 16/16, "infinitato", 1);
model.addBox(0/16, 1/16, 0/16, 1/16, 23/16, 16/16, "infinitato", 0);
model.addBox(0/16, 0/16, 0/16, 16/16, 1/16, 16/16, "infinitato", 2);
model.addBox(0/16, 23/16, 0/16, 16/16, 24/16, 16/16, "infinitato", 2);
render.addEntry(model);

TileEntity.registerPrototype(BlockID.infinitato, {
     defaultValues: {
		 
     },
     tick: function(){
		 
     },
     click: function(id, count, data, coords){
		 Entity.addEffect(Player.get(), 21, 3, 3600, false, false);//10 health
		 Entity.addEffect(Player.get(), 1, 0, 3600, false, false);//Speed
		 Entity.addEffect(Player.get(), 3, 1, 3600, false, false);//Haste II
		 Entity.addEffect(Player.get(), 5, 1, 3600, false, false);//Strength II
		 Entity.addEffect(Player.get(), 22, 0, 3600, false, false);//Absorption
		 Entity.addEffect(Player.get(), 23, 0, 3600, false, false);//Saturation
		 Entity.addEffect(Player.get(), 8, 0, 3600, false, false);//Jump Boost
		 Entity.addEffect(Player.get(), 10, 1, 3600, false, false);//Regeneration II
		 Entity.addEffect(Player.get(), 11, 1, 3600, false, false);//Resistance II
		 Entity.addEffect(Player.get(), 12, 0, 3600, false, false);//Fire Resistance
		 Entity.addEffect(Player.get(), 13, 0, 3600, false, false);//Water Breathing
     }
});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:BlockID.infinitato,
    count:1}, [
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
    a:{id:ItemID.tinyPotato}, b:{id:264}, c:{id:ItemID.catalystInfinity}
});

                //gaia block

IDRegistry.genBlockID("gaiaBlock");
Block.createBlock("gaiaBlock", [{
	name: "Gaia block",
	texture: [["block_gaia", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.gaiaBlock, 3);
ToolAPI.registerBlockMaterial(BlockID.gaiaBlock, "stone", 4, true);


      //flowers
	  
var BLOCK_TYPE_HIGHT_LIGHT = Block.createSpecialType({ 
lightlevel: 15,
lightopacity: 0 });

IDRegistry.genBlockID("asgWhite");
Block.createBlock("asgWhite", [{
	name: "Asgardandelion",
	texture: [["empty", 0], ["empty", 0],
		["asgardandelion", 1]],
	inCreative: false
}], BLOCK_TYPE_HIGHT_LIGHT);
Renderer.setSaplingRender(BlockID.asgWhite, 0);

Block.setDestroyTime(BlockID.asgWhite,0);
Block.registerDropFunction("asgWhite", function(coords, id, data, diggingLevel, toolLevel){
     return [[ItemID.asgWhite, 1, 0]]; 
});

IDRegistry.genItemID("asgWhite");
Item.createItem("asgWhite", "Asgardandelion", {
	name: "asgardandelion"
});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.asgWhite,
    count:1}, [
    "   aaa   ",
    "  aaaaa  ",
	"  aabaa  ",
	"  aaaaa  ",
	"   aaa   ",
	" cc d cc ",
	"ccccdcccc",
	" cc d cc ",
	"    d    "
    ], {
    a:{id:ItemID.ingotInfinity}, b:{id:ItemID.catalystInfinity}, c:{id:ItemID.neutron_nugget}, d:{id:ItemID.ingotNeutronium}
});

TileEntity.registerPrototype(BlockID.asgWhite, {
     defaultValues: {
		 curMana:9999999999999,
		 maxMana:9999999999999
     },
	 tick: function(){
		 //patom postavlyu timer
		 if(this.curMana < 9999999999999){
			 this.curMana = 999999999999;
		 }
     },
	 });



IDRegistry.genBlockID("solWhite");
Block.createBlock("solWhite", [{
	name: "Soarleander",
	texture: [["empty", 0], ["empty", 0],
		["soarleander", 1]],
	inCreative: false
}]);
Renderer.setSaplingRender(BlockID.solWhite, 0);

Block.setDestroyTime(BlockID.solWhite,0);
Block.registerDropFunction("solWhite", function(coords, id, data, diggingLevel, toolLevel){
     return [[ItemID.solWhite, 1, 0]]; 
});

IDRegistry.genItemID("solWhite");
Item.createItem("solWhite", "Soarleander", {
	name: "soarleander"
});

Callback.addCallback("ItemUse", function (coords, item, block){
if(item.id==ItemID.asgWhite && block.id== 2){
    Player.setCarriedItem(item.id, item.count - 1,item.data);
    World.setBlock(coords.x, coords.y+1, coords.z, BlockID.asgWhite, 0);
}
});

Callback.addCallback("ItemUse", function (coords, item, block){
	if(item.id==ItemID.solWhite && block.id== 2){
Player.setCarriedItem(item.id, item.count - 1,item.data);
World.setBlock(coords.x, coords.y+1, coords.z, BlockID.solWhite, 0);
}
});


