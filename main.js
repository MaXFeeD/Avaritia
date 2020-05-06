/*
BUILD INFO:
  dir: dev
  target: main.js
  files: 9
*/



// file: ItemsAV.js

IMPORT("ToolType");
IMPORT("SoundAPI");

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


var BHMesh = new RenderMesh(__dir__ + "models/blackhole.obj", "obj", {
scale:[0.1,0.1,0.1],
translate:[0,-1.2,0]
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
        return {
            "main": ModelBH
        };
    }
});

BlackHole.customizeDescription({
    getHitbox: function() {
        return {
            w: 0,
            h: 0
        };
    }
});

IDRegistry.genItemID("customEntitySpawn"); 
Item.createItem("customEntitySpawn", "Spawn custom entity", {name: "stick"});
Item.registerUseFunction("customEntitySpawn", function(coords, item, block){
    Entity.spawnCustom("BlackHole", coords.relative.x + .5, coords.relative.y + .5, coords.relative.z + .5); 
});


IDRegistry.genItemID("crystal_matrix_ingot");
Item.createItem("crystal_matrix_ingot", "Crystal matrix ingot", {name: "crystal_matrix_ingot"});

IDRegistry.genItemID("diamond_lattice");
Item.createItem("diamond_lattice", "Diamond lattice", {name: "diamond_lattice"});

IDRegistry.genItemID("neutron_pile");
Item.createItem("neutron_pile", "Neutronium pile", {name: "neutron_pile"});

IDRegistry.genItemID("neutron_nugget");
Item.createItem("neutron_nugget", "Neutronium nugget", {name: "neutron_nugget"});

IDRegistry.genItemID("ingotNeutronium");
Item.createItem("ingotNeutronium", "Neutronium ingot", {name: "neutronium_ingot"});

IDRegistry.genItemID("catalystInfinity");
Item.createItem("catalystInfinity", "Infinity catalyst", {name: "infinity_catalyst"});

IDRegistry.genItemID("ingotInfinity");
Item.createItem("ingotInfinity", "Infinity ingot", {name: "infinity_ingot"});

IDRegistry.genItemID("record_fragment");
Item.createItem("record_fragment", "Record Fragment", {name: "record_fragment"});

IDRegistry.genItemID("ironsing");
Item.createItem("ironsing", "Iron Singularity", {name: "singularity_iron"});

IDRegistry.genItemID("goldsing");
Item.createItem("goldsing", "Gold Singularity", {name: "singularity_gold"});

IDRegistry.genItemID("lapissing");
Item.createItem("lapissing", "Lapis Singularity", {name: "singularity_lapis"});

IDRegistry.genItemID("redstonesing");
Item.createItem("redstonesing", "Redstone Singularity", {name: "singularity_redstone"});

IDRegistry.genItemID("quartzsing");
Item.createItem("quartzsing", "Quartz Singularity", {name: "singularity_quartz"});

/*IDRegistry.genItemID("oreFractured");
Item.createItem("oreFractured", "Fractured ore", {name: "fractured_ore"});
IDRegistry.genItemID("infChunck");
Item.createItem("infChunck", "Infinity chunck", {name: "resource_infinity_drop"});

*/

IDRegistry.genItemID("gypMatter");
Item.createItem("gypMatter", "Ghyper Matter", {name: "gypmatter"});

IDRegistry.genItemID("endestPearl");
Item.createThrowableItem("endestPearl", "Endest pearl", {name: "endest"}, {stack: 16});


var endparticle = Particles.registerParticleType({
  texture: "ep",
  size: [1, 1],
  lifetime: [40, 40],
  render: 2,
  velocity: [0, -0.1, 0]
});

	
function handleExplode(coords, time) {
 Threading.initThread("explode", function() {
  java.lang.Thread.sleep(time * 50);
  Entity.remove("BlackHole");
  World.explode(coords.x, coords.y, coords.z, 7, false);
 }, 0);
}


Callback.addCallback('ProjectileHit', function (projectile, item, target, coords) {
	Player.setCarriedItem(item.id, item.count - 1, 0);
    coords = Entity.getPosition(projectile);
    let fmobs = Entity.getAllInRange(coords, 8);
Entity.spawnCustom("BlackHole", coords.x, coords.y, coords.z); 
for(var i in fmobs){
    Entity.moveToTarget(fmobs[i], coords.x, coords.y, coords.z, {speed: 1, denyY: false, jumpVel: 0});
}
    for (var dx = -5; dx < 5; dx++) {
    for (var dy = -3; dy < 5; dy++) {
    for (var dz = -5; dz < 5; dz++) {	
         if(dx * dx + dy * dy + dz * dz <= 20){
	     Particles.addParticle(endparticle, coords.x+0.5 + dx, coords.y+1 + dy, coords.z+0.5 + dz, 0, -0.1, 0, 1);	
	}}}
};
     handleExplode(coords, 20 * 9);
});


var claster = new ItemExtraData();

var DroppedItems = [];
Callback.addCallback("EntityAdded", function(entity){
if(Entity.getType(entity) == Native.EntityType.ITEM) DroppedItems.push(entity);
});




// file: ToolsAV.js

IMPORT("Bow");

IDRegistry.genItemID("cosmSword");
Item.createItem("cosmSword", "Sword of the Cosmos", {name: "cosm_sword", meta: 0}, {stack: 1});
Item.setEnchantType("cosmSword", 16, 999999999);

IDRegistry.genItemID("skull_sword");
Item.createItem("skull_sword", "Skull sword", {name: "skull_sword", meta: 0}, {stack: 1});
Item.setEnchantType("skull_sword", 14, 25);

IDRegistry.genItemID("infbow");
Item.createItem("infbow", "Inf Bow", { name: "bow_idle", meta: 0 }, { stack: 1 });
Item.describeItem(ItemID.infbow, {
	toolRender: true,
	maxDamage: 999999999, 
	useAnimation: 4
});
var infbow = new Bow();
infbow.set({
	id: ItemID.infbow,
	texture: "bow_pull",
	bullets: [ItemID.infbow], 
	skin: "mob/heavenarrow.png", 
	speed: 10, 
	damage: 60, 
	variations: 3, 
});

IDRegistry.genItemID("cosmPickaxe");
Item.createItem("cosmPickaxe", "World Braker", {name: "infpickaxe", meta: 0}, {stack: 1});
Item.setEnchantType("cosmPickaxe", 18, 10);

IDRegistry.genItemID("cosmhammer");
Item.createItem("cosmhammer", "hammer", {name: "infhammer", meta:0}, {stack: 1});

IDRegistry.genItemID("cosmShovel");
Item.createItem("cosmShovel", "Planet Eater", {name: "infshovel", meta: 0}, {stack: 1});

IDRegistry.genItemID("cosmdes");
Item.createItem("cosmdes", "destroyer", {name: "infdestroyer", meta: 0}, {stack: 1});

IDRegistry.genItemID("cosmAxe");
Item.createItem("cosmAxe", "Nature's Ruin", {name: "infaxe", meta: 0}, {stack: 1});

IDRegistry.genItemID("cosmHoe");
Item.createItem("cosmHoe", "Hoe of the Green Earth", {name: "infhoe", meta: 0}, {stack: 1});


ToolAPI.addToolMaterial("cosmsw", {durability: 999999999, level: 7, efficiency: 6, damage: 99999999999999, enchantability: 14});
ToolAPI.addToolMaterial("skull_sword", {durability: 999999999, level: 7, efficiency: 6, damage: 999, enchantability: 14});
ToolAPI.addToolMaterial("cosmsh", {durability: 999999999, level: 8, efficiency: 8, damage: 10, enchantability: 14});
ToolAPI.addToolMaterial("cosmpi", {durability: 999999999, level: 10, efficiency: 10, damage: 7, enchantability: 14});
ToolAPI.addToolMaterial("cosmaxe", {durability: 999999999, level: 9, efficiency: 9, damage: 10, enchantability: 14});
ToolAPI.addToolMaterial("cosmhoe", {durability: 999999999, level: 9, efficiency: 9, damage: 6, enchantability: 14});
ToolAPI.addToolMaterial("cosmbow", {durability: 999999999, level: 9, efficiency: 9, damage: 1, enchantability: 14});

ToolAPI.setTool(ItemID.cosmSword, "cosmsw", ToolType.sword);
Item.setToolRender(ItemID.cosmSword, true);

ToolAPI.setTool(ItemID.skull_sword, "skull_sword", ToolType.sword);
Item.setToolRender(ItemID.skull_sword, true);

ToolAPI.setTool(ItemID.cosmShovel, "cosmsh", ToolType.shovel);
Item.setToolRender(ItemID.cosmShovel, true);

ToolAPI.setTool(ItemID.cosmPickaxe, "cosmpi", ToolType.pickaxe);
Item.setToolRender(ItemID.cosmPickaxe, true);

ToolAPI.setTool(ItemID.cosmAxe, "cosmaxe", ToolType.axe);
Item.setToolRender(ItemID.cosmAxe, true);

ToolAPI.setTool(ItemID.cosmHoe, "cosmhoe", ToolType.hoe);
Item.setToolRender(ItemID.cosmHoe, true);

ToolAPI.setTool(ItemID.infbow, "cosmbow", ToolType.sword);
Item.setToolRender(ItemID.infbow, true);

ToolAPI.setTool(ItemID.cosmhammer, "cosmpi", ToolType.pickaxe);
Item.setToolRender(ItemID.cosmhammer, true);

ToolAPI.setTool(ItemID.cosmdes, "cosmsh", ToolType.shovel);
Item.setToolRender(ItemID.cosmdes, true);

              //functional

/*
Callback.addCallback("EntityHurt", function (a,v) {
    if(Player.getCarriedItem().id == ItemID.cosmSword){ 
    Entity.damageEntity(v, Entity.getHealth(v));
    }
});*/
  
Callback.addCallback('EntityDeath', function (entity, attacker, damageType) {
	var et = Entity.getType(entity); 
    var coord = Entity.getPosition(entity);
if(Player.getCarriedItem().id == ItemID.skull_sword){
if(et==48||et==34){
World.drop(coord.x,coord.y,coord.z,397,1,1);
}}
});

	
Callback.addCallback('ItemUse', function (coords, item, block) {
	var player = Player.get();
	if(item.id == ItemID.cosmPickaxe && Entity.getSneaking(player)){
		Player.setCarriedItem(item.id, item.count - 1, 0);
		Player.setCarriedItem(ItemID.cosmhammer, 1, 0);
		}
});

Callback.addCallback('ItemUse', function (coords, item, block) {
	var player = Player.get();
    if(item.id == ItemID.cosmShovel && Entity.getSneaking(player)){
	   Player.setCarriedItem(item.id, item.count - 1, 0);
	   Player.setCarriedItem(ItemID.cosmdes, 1, 0);
	}
});
	

Callback.addCallback('ItemUse', function (coords, item, block) {
var player = Player.get();
if(item.id == ItemID.cosmHoe && Entity.getSneaking(player) && (block.id==2 || block.id==3) && coords.side==1){
for (xc = coords.x - 6; xc < coords.x + 6; xc++) {
for (zc = coords.z - 6; zc < coords.z + 6; zc++) {
if(World.getBlockID(xc, coords.y, zc) == 3 || World.getBlockID(xc, coords.y, zc) == 2){
World.setBlock(xc, coords.y, zc, 60);
}}}
}
});


Callback.addCallback('ItemUse', function (coords, item, block) {
	var player = Player.get();
	if(item.id == ItemID.cosmhammer && Entity.getSneaking(player)){
		Player.setCarriedItem(item.id, item.count - 1, 0);
		Player.setCarriedItem(ItemID.cosmPickaxe, 1, 0);
		}
});
Callback.addCallback("DestroyBlock", function(coords, block, player){
var side = coords.side;
var X = 8;
var Y = 9;
var Z = 7;
if(side == 4 || side == 5){
         //   X = 0;
		 }
            if(side == 1 || side == 6){
            Y = 0;}
            if(side == 2 || side == 3){
          //  Z = 0;
		  }
      for(var xx = coords.x - X; xx <= coords.x + X; xx++){
      for(var yy = coords.y - 1 /*Y*/; yy <= coords.y + Y; yy++){
      for(var zz = coords.z - Z; zz <= coords.z + Z; zz++){
item=Player.getCarriedItem(true);
if(World.getBlockID(xx, yy, zz) !== 7 && item.id == ItemID.cosmhammer){
    World.setBlock(xx, yy, zz, 0);
	//World.destroyBlock(xx, yy, zz, true);
	}}
  }};
});


Callback.addCallback('ItemUse', function (coords, item, block) {
var player = Player.get();
if(item.id == ItemID.cosmdes && Entity.getSneaking(player)){
	Player.setCarriedItem(item.id, item.count - 1, 0);
	Player.setCarriedItem(ItemID.cosmShovel, 1, 0);
	}
});
Callback.addCallback("DestroyBlock", function(coords, block, player){
	var side = coords.side;
var X = 8;
var Y = 9;
var Z = 7;
if(side == 4 || side == 5){
       //     X = 0;
			}
            if(side == 1 || side == 6){
            Y = 9;
			}
            if(side == 2 || side == 3){
       //     Z = 0;
			}
      for(var xx = coords.x - X; xx <= coords.x + X; xx++){
      for(var yy = coords.y - 1 /*Y*/; yy <= coords.y + Y; yy++){
      for(var zz = coords.z - Z; zz <= coords.z + Z; zz++){
item = Player.getCarriedItem(true);
if(World.getBlockID(xx, yy, zz) !== 14 && World.getBlockID(xx, yy, zz) !== 15 && World.getBlockID(xx, yy, zz) !== 16 && World.getBlockID(xx, yy, zz) !== 21 && World.getBlockID(xx, yy, zz) !== 56 && World.getBlockID(xx, yy, zz) !== 73 && World.getBlockID(xx, yy, zz) !== 129 && World.getBlockID(xx, yy, zz) !== 49 && World.getBlockID(xx, yy, zz) !== 7 && item.id==ItemID.cosmdes){
    World.setBlock(xx, yy, zz, 0);
	//World.destroyBlock(xx, yy, zz, true);
}}
}};
});


Callback.addCallback('ProjectileHit', function (projectile, item, target, coords) {
	if(projectile.id == ItemID.infbow /*&& target !== Entity.getAll()*/ && item.id == ItemID.infbow){
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
		Entity.spawn(coords.x+0.5, coords.y+3, coords.z+1.5, 80, [heavenarrow]);
}
});


Callback.addCallback('ItemUse', function (coords, item, block) {
	var player = Player.get();
	if(item.id==ItemID.cosmAxe && Entity.getSneaking(player)){
		for (xco = coords.x - 13; xco < coords.x + 13; xco++) {
        for (zco = coords.z - 13; zco < coords.z + 13; zco++) {
	    for (yco = coords.y - 20; yco < coords.y +20; yco++){
           if(World.getBlockID(xco, yco, zco) == 2){
               World.setBlock(xco, yco, zco, 3);
	}
	       if(World.getBlockID(xco, yco, zco) == 17 || World.getBlockID(xco, yco, zco) == 18 || World.getBlockID(xco, yco, zco) == 31 || World.getBlockID(xco, yco, zco) == 38){
			   World.setBlock(xco, yco, zco, 0);
		   }
	}}}}
	});
	

var rr = 3;

var TreeCapitator = {
	registerAxe: function(axe){
Callback.addCallback("DestroyBlock", function(coords, block, player){
	var player = Player.get();
if(Player.getCarriedItem().id == axe && !Entity.getSneaking(player) && block.id == 17 || block.id == 18){
var startx = coords.x - rr;
var starty = coords.y - rr;
var startz = coords.z - rr;
var range = rr + rr;
for(var xx = 0; xx < range; xx++){
for(var yy = 0; yy < 60; yy++){
for(var zz = 0; zz < range; zz++){
if(World.getBlockID(startx + xx, starty + yy, startz + zz) == block.id){World.destroyBlock(startx + xx, starty + yy, startz + zz, true);
};
}}};
}});
}};


ModAPI.registerAPI("TreeCapitator", TreeCapitator);
TreeCapitator.registerAxe(ItemID.cosmAxe);




// file: BlocksAV.js

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

/*IDRegistry.genBlockID("gaiaBlock"); 
Block.createBlock("gaiaBlock", [
    {name: "Gaia block", texture: [["block_gaia", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.gaiaBlock,3);
ToolAPI.registerBlockMaterial(BlockID.gaiaBlock, "stone", 4, true);
*/
IDRegistry.genBlockID("infBlock"); 
Block.createBlock("infBlock", [
    {name: "Infinity block", texture: [["infinity", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.infBlock,3);
ToolAPI.registerBlockMaterial(BlockID.infBlock, "stone", 4, true);

IDRegistry.genBlockID("neutroniumBlock"); 
Block.createBlock("neutroniumBlock", [
    {name: "Neutronium block", texture: [["neutronium", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.neutroniumBlock,3);
ToolAPI.registerBlockMaterial(BlockID.neutroniumBlock, "stone", 4, true);

IDRegistry.genBlockID("compreBlock"); 
Block.createBlock("compreBlock", [
    {name: "Compresed workbench", texture: [["compressed", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.compreBlock,4);
ToolAPI.registerBlockMaterial(BlockID.compreBlock, "wood", 0, true);

IDRegistry.genBlockID("dcompreBlock"); 
Block.createBlock("dcompreBlock", [
    {name: "Double compressed workbench", texture: [["double_compressed", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.dcompreBlock,6);
ToolAPI.registerBlockMaterial(BlockID.dcompreBlock, "wood", 2, true);

IDRegistry.genBlockID("extWorckbench");
Block.createBlockWithRotation("extWorckbench", [
 {name: "Extrеme workbench", texture:
 [["crafting", 2],
 ["craftingtop", 0],
 ["craftingside", 0],
  ["craftingside", 0],
   ["craftingside", 0],
    ["craftingside", 0]],
 inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.extWorckbench,4);
ToolAPI.registerBlockMaterial(BlockID.extWorckbench, "stone", 3, true);

IDRegistry.genBlockID("crystal_matrixAV");
Block.createBlockWithRotation("crystal_matrixAV", [
	{name: "Crystal matrix", texture: 
	[["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0]],
	      inCreative: true}
], "opaque");
Block.setDestroyTime(BlockID.crystal_matrixAV,4);
ToolAPI.registerBlockMaterial(BlockID.crystal_matrix, "stone", 4, true);

IDRegistry.genBlockID("compressorAv"); 
Block.createBlockWithRotation("compressorAv", [
    {name: "Compressor neutronium", texture: [["side", 0],["top_comp", 0],["side", 0],["compfront", 0],["side", 0],["side", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.compressorAv,4);
ToolAPI.registerBlockMaterial(BlockID.compressorAv, "stone", 4, true);

IDRegistry.genBlockID("neutCo"); 
Block.createBlockWithRotation("neutCo", [
    {name: "Neutroinium collector", texture: [["side", 0],["top", 0],["side", 0],["active", 0],["side", 0],["side", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.neutCo,4);
ToolAPI.registerBlockMaterial(BlockID.neutCo, "stone", 4, true);

/*
IDRegistry.genBlockID("asgWhite");
Block.createBlock("asgWhite", [
    {name: "Asgardandelion", texture: [["empty", 0], ["empty", 0], ["asgardandelion", 1]], inCreative: false}
]);

IDRegistry.genItemID("asgWhite");
Item.createItem("asgWhite", "Asgardandelion", {name: "asgardandelion"});

Item.registerUseFunction("asgWhite", function(coords, item, block){
    var place = coords.relative;
    if(GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))){
        World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
        Player.setCarriedItem(item.id, item.count - 1, item.data);  
    }
});

Renderer.setSaplingRender(BlockID.asgWhite,0);

Callback.addCallback("GenerateChunk", function(x,z){ 
for(var i = 0; i < 1; i++){ 
coords=GenerationUtils.randomCoords(x,z,20,96); 
for(var k=20;k<96;k++){ 
if(World.getBlockID(coords.x,k,coords.z)==3){ 
if(World.getBlockID(coords.x,k+1,coords.z)!=0)return 
World.setBlock(coords.x,k+1,coords.z,BlockID.Asgardandelion,0); 
} 
} 
} 
});

Recipes.addShaped({id: ItemID.gypMatter, count: 1, data: 0}, [
    "oao"  
], ['a', ItemID.asgWhite, 0]);


IDRegistry.genBlockID("solWhite");
Block.createBlock("solWhite", [
    {name: "Soarleander", texture: [["empty", 0], ["empty", 0], ["soarleander", 1]], inCreative: false}
]);

IDRegistry.genItemID("solWhite");
Item.createItem("solWhite", "Soarleander", {name: "soarleander"});

Item.registerUseFunction("solWhite", function(coords, item, block){
    var place = coords.relative;
    if(GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))){
        World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
        Player.setCarriedItem(item.id, item.count - 1, item.data);  
    }
});

Renderer.setSaplingRender(BlockID.solWhite,0);

Callback.addCallback("GenerateChunk", function(x,z){ 
for(var i = 0; i < 1; i++){ 
coords=GenerationUtils.randomCoords(x,z,20,96); 
for(var k=20;k<96;k++){ 
if(World.getBlockID(coords.x,k,coords.z)==3){ 
if(World.getBlockID(coords.x,k+1,coords.z)!=0)return 
World.setBlock(coords.x,k+1,coords.z,BlockID.solWhite,0); 
} 
} 
} 
});*/




// file: FoodAV.js

IDRegistry.genItemID("ultimstew");
Item.createFoodItem("ultimstew", "Cosmos soup", {name: "ultimstew", meta: 0},{isTech:false,stack: 64,food: 10});
Callback.addCallback("FoodEaten",function(heal, satRatio){
if(Player.getCarriedItem().id==ItemID.ultimstew){
Entity.addEffect(Player.get(), 12, 1, 12000, false,false);
Entity.addEffect(Player.get(), 5, 1, 12000, false,false);
Entity.addEffect(Player.get(), 6, 1, 30, false,false);
Entity.addEffect(Player.get(), 21, 2, 12000, false,false);
Entity.addEffect(Player.get(), 22, 2, 12000, false,false);
}});

IDRegistry.genItemID("cosmMeatballs");
Item.createFoodItem("cosmMeatballs", "Cosmos meatballs", {name: "cosm_meatballs", meta: 0},{isTech:false,stack: 64,food: 10});
Callback.addCallback("FoodEaten",function(heal, satRatio){
if(Player.getCarriedItem().id==ItemID.cosmMeatballs){
Entity.addEffect(Player.get(), 12, 1, 12000, false,false);
Entity.addEffect(Player.get(), 1, 2, 12000, false,false);
Entity.addEffect(Player.get(), 5, 1, 12000, false,false);
Entity.addEffect(Player.get(), 6, 1, 30, false,false);
Entity.addEffect(Player.get(), 21, 2, 12000, false,false);
Entity.addEffect(Player.get(), 22, 2, 12000, false,false);
}});






// file: gui.js

IMPORT("RecipeTileEntityLib");

		           //table1

var container = new UI.Container();  
var GuiComp = new UI.StandartWindow({
	standart: {header: {text: {text: Translation.translate("Compressed crafting table")}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, 
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"text_0": {type: "text", x: 338, y: 260, width: 120, height: 16, text: "Хули ты сюда смотришь?!! Этот верстак не робочий!!!"},
	}
});		
TileEntity.registerPrototype(BlockID.compreBlock, {
  defaultValues: {},
  getGuiScreen: function(){
    return GuiComp;
  },
  tick: function(){}
});

               //table2

var GuiDouble = new UI.StandartWindow({
	standart: {header: {text: {text: Translation.translate("Double compressed crafting table")}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, 
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"text_0": {type: "text", x: 360, y: 260, width: 120, height: 16, text: "Я же сказал бля, этот верстак тож не роботает!!!"},
	}
});		
TileEntity.registerPrototype(BlockID.dcompreBlock, {
  defaultValues: {},
  getGuiScreen: function(){
    return GuiDouble;
  },
  tick: function(){}
});

         //general table

/*let slot = this.container.getSlot("slot");
if(slot.id == 266) return true*/
var extWorckbench = new UI.StandartWindow({
	standart: {header: {text: {text: "Extreme crafting table"}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")},
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"scale_0": {type: "scale", x: 843, y: 252, direction: 0, bitmap: "arrow", scale: 3.2, value: 1},
		/*"guid": {type: "button", x: 920, y: 50, scale: 1.9, bitmap: "guid", bitmap2: "guid_press",
		clicker: {onClick: function(){
		container.openAs(guid1);
		}}
	    }, */
		"outputSlot": {type: "slot", x: 930, y: 250, size: 50},
		}
		});
		
let gc = extWorckbench.getContent();
let ryad = 0;
let xx = 380;
let yy = 50;
for (let i = 0; i < 81; i++) 
{
    gc.elements["inputSlot" + i] = 
	{
        type: "slot",
        x: xx,
        y: yy,
		size: 50
    };
    xx += 50;
    ryad++;
    if (ryad == 9) 
	{
        xx = 380;
        yy += 50;
        ryad = 0;
    }
}
		
RecipeTE.registerWorkbench("extWorckbench",
{
    rows:9,
    cols:9,
    GuiScreen:extWorckbench,
});

              //guid

var guid1 = new UI.StandartWindow({
	standart: {
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, },
	drawing: [],
	elements: {
		"image_0": {type: "image", x: 142, y: 10, bitmap: "extreme_nei", scale: 2.85},
		//"slot_0": {type: "slot", x: 173, y: 73, size: 52, visual: false, bitmap: "custom.slot_default", needClean: false, isTransparentBackground: false},
		"left": {type: "button", x: 20, y: 210, scale: 3.15, bitmap: "guid_left", bitmap2: "guid_left_press", 
		   clicker: {onClick: function(){
		      container.openAs(extWorckbench);
			  container.close(guid1);
		}}
		},
		"right": {type: "button", x: 880, y: 210, scale: 3.15, bitmap: "guid_right", bitmap2: "guid_right_press"},
		"result": {type: "slot", x: 726, y: 266, size: 74, visual: true},
		"name": {type: "text", x: 310, y: 30, width: 120, height: 16, text: "This is a Text element"},
	}
});
let g1 = guid1.getContent();
let r1 = 0;
let xg1 = 173;
let yg1 = 73;
for (let i = 0; i < 81; i++) 
{
    g1.elements["slot" + i] = 
	{
        type: "slot",
        x: xg1,
        y: yg1,
		size: 52,
		visual: true,
    };
    xg1 += 52;
    r1++;
    if (r1 == 9) 
	{
        xg1 = 173;
        yg1 += 52;
        r1 = 0;
    }
}


             //collector

var guiCollector = new UI.StandartWindow({
	standart: {header: {text: {text: "Collector"}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")},
	inventory: {standart: true}},
	drawing: [],
	elements: {
		"slot_0": {type: "slot", x: 570, y: 160, size: 102}, 
		"progress": {type: "text", x: 545, y: 290, width: 120, height: 16, text: "Progress: 0%"},
	}
});

const COLLECTOR_MAX = 6000;

TileEntity.registerPrototype(BlockID.neutCo, {
defaultValues: {
 jin:0,
},
getGuiScreen: function(){
 return guiCollector;
},
tick: function() {
 slot = this.container.getSlot("slot_0");
 this.container.setText("progress", "Progress: " + parseInt(this.data.jin / COLLECTOR_MAX * 100) + "%");
 if (++this.data.jin >= COLLECTOR_MAX && slot.count < 64){
  if (slot.id == ItemID.neutron_pile) {
   slot.count ++;
   this.data.jin = 0;
  } else if (slot.id == 0 && !slot.count) {
   slot.id = ItemID.neutron_pile;
  } else this.data.jin = COLLECTOR_MAX;
 }
},
   getTransportSlots: function(){
		let inputC = [];
	   let outputC = [];
	 	  for(i=0;i<1;i++){
	     inputC.push("slot"+i); 
	     outputC.push("slot"+i);
		 }
  return {input: inputC, output: outputC}
	 	},
});

            //compressor
			
var compressorGUI = new UI.StandartWindow({
	standart: {header: {text: {text: "Compressor"}},
	background: {color: android.graphics.Color.parseColor("#c6c6c6")}, inventory: {standart: true}},
	drawing: [{type:"bitmap",x:574,y:238,scale:3.6,bitmap:"progress_background"},],
	elements: {
		"slot_0": {type: "slot", x: 500, y: 230, size: 68, visual: false, needClean: false, isTransparentBackground: false},
		"progress": {type: "scale", x: 574, y: 238, direction: 0, bitmap: "progress", scale: 3.6, value: 1},
		"sing": {type: "scale", x: 657, y: 228, direction: 0, bitmap: "sing", scale: 2.3, value: 1},
		"slot_1": {type: "slot", x: 740, y: 230, size: 68, visual: false, needClean: false, isTransparentBackground: false},
		"count": {type: "text", x: 580, y: 310, width: 120, height: 16, text: "0/0"},
		"block": {type: "text", x: 580, y: 360, width: 120, height: 16, text: ""},
	}
});

		
var ModRecipe = {
	recipe:{},
	addRecipe:function(a,b){
		this.recipe[a] = b;
		},
		getRecipe: function (a){
			if(this.recipe[a]){
				return this.recipe[a];
				}
		},
	};

ModRecipe.addRecipe(42,{
	count:850,
	out:ItemID.ironsing,
	name: "Iron",
	});

ModRecipe.addRecipe(41,{
	count:650,
	out:ItemID.goldsing,
	name: "Gold",
	});

ModRecipe.addRecipe(155,{
	count:750,
	out:ItemID.quartzsing,
	name: "Quartz",
	});

ModRecipe.addRecipe(152,{
	count:950,
	out:ItemID.redstonesing,
	name: "Redstone",
	});

ModRecipe.addRecipe(22,{
	count:850,
	out:ItemID.lapissing,
	name: "Lapis",
	});




TileEntity.registerPrototype(BlockID.compressorAv,{
	defaultValues:{
		count:0,
		progress:0,
		end:0,
		id:0,
		block: "",
		},
		tick:function(){
			var slot0 = this.container.getSlot("slot_0");
			var slot1 = this.container.getSlot("slot_1");
			var recipe = ModRecipe.getRecipe(slot0.id);
			if(recipe){
			if(this.data.id==0&&slot0.id!=0){
				this.data.id = slot0.id;
				}
			if(slot0.id==this.data.id&&slot0.id!=0){
				this.data.block = recipe.name;
				this.data.count += 1;
				this.data.end = recipe.count; 
				this.data.progress += 1/this.data.end;
				slot0.count--;
				}
				if(this.data.progress >= 1){
					slot1.id = recipe.out;
					slot1.count++;
					this.data.count -= this.data.end;
					this.data.id = 0;
					this.data.progress = 0;
					}
				}
				this.container.setText("block", "Block of " + this.data.block);
				this.container.setText("count",this.data.count +" / "+this.data.end);
				this.container.setScale("progress",this.data.progress);
				this.container.validateAll();
			},
	getGuiScreen:function(){
		return compressorGUI;
		}
	});
  
 




// file: anim.js

/*var Animate = function(IDD,M,f){ 
var FtP = World.getTime / f; 
var ind = 0;
if(FtP > 0){ 
Item.registerIconOverrideFunction(IDD, function(item, name){ 
return {name: name, meta: M+1}
 }); 
 } 
};
Animate(ItemID.cosmMeatballs, 0, 7);
*/

var two = 0;
var sev = 0;
var fou = 0;
var eit = 0;
var ts = 0;
var tre = 0;
var fiv = 0;
var setRequiresIconOverride = ModAPI.requireGlobal("Item.setRequiresIconOverride");

Item.registerIconOverrideFunction(ItemID.ironsing, function(item, texture){
return {name: "singularity_iron", meta: fiv};
});
Item.registerIconOverrideFunction(ItemID.goldsing, function(item, texture){
return {name: "singularity_gold", meta: fiv};
});
Item.registerIconOverrideFunction(ItemID.lapissing, function(item, texture){
return {name: "singularity_lapis", meta: fiv};
});
Item.registerIconOverrideFunction(ItemID.quartzsing, function(item, texture){
return {name: "singularity_quartz", meta: fiv};
});
Item.registerIconOverrideFunction(ItemID.redstonesing, function(item, texture){
return {name: "singularity_redstone", meta: fiv};
});
Item.registerIconOverrideFunction(ItemID.endestPearl, function(item, texture){
return {name: "endest", meta: fou};
});
Item.registerIconOverrideFunction(ItemID.cosmMeatballs, function(item, texture){
return {name: "cosm_meatballs", meta: sev};
});
Item.registerIconOverrideFunction(ItemID.inf_chestplate, function(item, texture){
return {name: "chestaplateAV", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.cosmhammer, function(item, texture){
return {name: "infhammer", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.cosmHoe, function(item, texture){
return {name: "infhoe", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.catalystInfinity, function(item, texture){
return {name: "infinity_catalyst", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.ingotInfinity, function(item, texture){
return {name: "infinity_ingot", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.cosmPickaxe, function(item, texture){
return {name: "infpickaxe", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.cosmShovel, function(item, texture){
return {name: "infshovel", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.cosmSword, function(item, texture){
return {name: "cosm_sword", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.inf_leggings, function(item, texture){
return {name: "legginsAV", meta: eit};
});
Item.registerIconOverrideFunction(ItemID.skull_sword, function(item, texture){
return {name: "skull_sword", meta: tre};
});
/*
Item.registerIconOverrideFunction(ItemID.orb_armok, function(item, texture){
return {name: "orb_armok", meta: tre};
});*/
Item.registerIconOverrideFunction(ItemID.ingotNeutronium, function(item, texture){
return {name: "neutronium_ingot", meta: two};
});
Item.registerIconOverrideFunction(ItemID.ultimstew, function(item, texture){
return {name: "ultimstew", meta: ts};
});

setRequiresIconOverride(ItemID.ironsing, true);
setRequiresIconOverride(ItemID.goldsing, true);
setRequiresIconOverride(ItemID.lapissing, true);
setRequiresIconOverride(ItemID.quartzsing, true);
setRequiresIconOverride(ItemID.redstonesing, true);
setRequiresIconOverride(ItemID.endestPearl, true);
setRequiresIconOverride(ItemID.cosmMeatballs, true);
setRequiresIconOverride(ItemID.inf_chestplate, true);
setRequiresIconOverride(ItemID.cosmhammer, true);
setRequiresIconOverride(ItemID.cosmHoe, true);
setRequiresIconOverride(ItemID.catalystInfinity, true);
setRequiresIconOverride(ItemID.ingotInfinity, true);
setRequiresIconOverride(ItemID.cosmPickaxe, true);
setRequiresIconOverride(ItemID.cosmShovel, true);
setRequiresIconOverride(ItemID.cosmSword, true);
setRequiresIconOverride(ItemID.inf_leggings, true);
setRequiresIconOverride(ItemID.skull_sword, true);
//setRequiresIconOverride(ItemID.orb_armok, true);
setRequiresIconOverride(ItemID.ingotNeutronium, true);
//setRequiresIconOverride(ItemID., true);
setRequiresIconOverride(ItemID.ultimstew, true);


Callback.addCallback("tick", function(){
	let time = World.getThreadTime()%4;
	if(time == 0 || time == 4){
		if(two < 2){
			two++;
		}else{
			two = 0;
		}
		if(tre < 3){
			tre++;
		}else{
			tre = 0;
		}
		if(fou < 4){
			fou++;
		}else{
			fou = 0;
		}
		if(fiv < 5){
			fiv++;
		}else{
			fiv = 0;
		}
		if(sev < 7){
			sev++;		
		}else{
			sev = 0;
		}
		if(eit < 8){
			eit++;
		}else{
			eit = 0;
		}
		if(ts < 27){
			ts++;
		}else{
			ts = 0;
		}
	}
});


    var inf_render = new Render(); 
    var partObj = [ 
        {
            type: "box",
            coords: {
                x: 0,
                y: 31,
                z: 0
            },
            size: {
                x: 64,
                y: 64,
                z: 0
            },
            uv: {
                x: 0,
                y: 0
            }
        }];
inf_render.setPart("head", partObj, {});
		
var coords = Player.getPosition();
var wing = new Animation.Base(coords.x, coords.y, coords.z);
wing.describe({ render: inf_render.getId(),
skin: "armor/infinity_armor_wing_full.png"});

var coords = wing.coords;
wing.setPos(coords.x, coords.y + 1, coords.z);
wing.refresh();

Callback.addCallback("tick", function() {
 var coords = Player.getPosition();
 wing.setPos(coords.x, coords.y + 1, coords.z);
 wing.refresh();
});

Callback.addCallback("LevelLeft", function() {
 wing.isLoaded = false;
 wing.leftTicks = 1;
});

/*
wing.leftTicks = 1;
wing.loadCustom(function() {
    this.setPos(this.coords.x + 0.05, this.coords.y, this.coords.z - 0.05); 
    this.refresh();
    if(this.leftTicks > 0) this.leftTicks--;
    else this.destroy();
});
*/
//wing.setInterpolationEnabled(true);





// file: arm.js

var arm = {
	setMode: function(arg){
       Callback.addCallback("tick",function() {
           if(Player.getArmorSlot(arg.type[0]).id == arg.id){
	          arg.tick();
    }})}
    
};

Game.getGameMode = ModAPI.requireGlobal("Level.getGameMode");

IDRegistry.genItemID("inf_helmet");
Item.createArmorItem("inf_helmet", "Infinity Helmet", {name: "helmetAV", meta: 0}, {	isTech: false,
	armor: 3,
	type: "helmet",
	texture: "armor/infinity_armor_full0.png",
	durability: 999999999
});
arm.setMode({
	id: ItemID.inf_helmet,
	type: [0],
	tick: function(){
	   Entity.addEffect(Player.get(), 16, 1, 19, false,false);
       Entity.addEffect(Player.get(), 23, 190, 19, false,false);
       Entity.addEffect(Player.get(), 13, 190, 19, false,false);
	}
});
IDRegistry.genItemID("inf_chestplate");
Item.createArmorItem("inf_chestplate", "Infinity Chestplate", {name: "chestaplateAV", meta: 0}, {	isTech: false,
	armor: 8,
	type: "chestplate",
	texture: "armor/infinity_armor_full0.png",
	durability: 999999999
});
arm.setMode({
	id: ItemID.inf_chestplate,
	type: [1],
	tick: function(){
	   Entity.addEffect(Player.get(), 11, 190, 19, false,false);
       Entity.addEffect(Player.get(), 10, 190, 19, false,false);
       Player.setFlyingEnabled(true);
	}
});
Callback.addCallback("tick",function() {
    if(Player.getArmorSlot(1).id ==! ItemID.inf_chestplate && !Game.getGameMode()){
	    Player.setFlyingEnabled(false);
    }
});

Callback.addCallback("tick",function() {
    if(Player.getArmorSlot(1).id == ItemID.inf_chestplate && Player.getFlying(true)){
	//    wing.load();
    }else {
		if(Player.getArmorSlot(1).id == ItemID.inf_chestplate || Player.getFlying(false)){
			wing.destroy();
		}
	}
});

IDRegistry.genItemID("inf_leggings");
Item.createArmorItem("inf_leggings", "Infinity Leggings", {name: "legginsAV", meta: 0}, {	isTech: false,
	armor: 6,
	type: "leggings",
	texture: "armor/infinity_armor_full1.png",
	durability: 999999999
});
arm.setMode({
	id: ItemID.inf_leggings,
	type: [2],
	tick: function(){
	   Entity.addEffect(Player.get(), 1, 9, 19, false,false);
	}
});

IDRegistry.genItemID("inf_boots");
Item.createArmorItem("inf_boots", "Infinity Boots", {name: "bootAV", meta: 0}, {	isTech: false,
	armor: 3,
	type: "boots",
	texture: "armor/infinity_armor_full0.png",
	durability: 999999999
});
arm.setMode({
	id: ItemID.inf_boots,
	type: [3],
	tick: function(){
	   Entity.addEffect(Player.get(), 8, 3, 19, false,false);
	}
});




// file: crafts.js


Callback.addCallback('LevelLoaded', function () {
Recipes.addShaped({id: ItemID.diamond_lattice, count: 1, data: 0}, [
"aoa",
"oao",
"aoa"], ['a', 264, 0]);
Recipes.addShaped({id: BlockID.neutroniumBlock, count: 1, data: 0}, [
"aaa",
"aaa",
"aaa"], ['a', ItemID.ingotNeutronium, 0]);
Recipes.addShaped({id: ItemID.neutron_nugget, count: 1, data: 0}, [
"aaa",
"aaa",
"aaa"], ['a', ItemID.neutron_pile, 0]);
Recipes.addShaped({id: ItemID.ingotNeutronium, count: 1, data: 0}, [
"aaa",
"aaa",
"aaa"], ['a', ItemID.neutron_nugget, 0]);
Recipes.addShaped({id: ItemID.crystal_matrix_ingot, count: 1, data: 0}, [
"aba",
"aba",
"ooo"], ['a', ItemID.diamond_lattice, 0, 'b', 399, 0]);
Recipes.addShaped({id: BlockID.compreBlock, count: 1, data: 0}, [
"aaa",
"aaa",
"aaa"], ['a', 58, 0]);
Recipes.addShaped({id: BlockID.dcompreBlock, count: 1, data: 0}, [
"aaa",
"aaa",
"aaa"], ['a', BlockID.compreBlock, 0]);
Recipes.addShaped({id: BlockID.crystal_matrixAV, count: 1, data: 0}, [
"aaa",
"aaa",
"aaa"], ['a', ItemID.crystal_matrix_ingot, 0]);
Recipes.addShaped({id: BlockID.extWorckbench, count: 1, data: 0}, [
"aaa",
"aba",
"aaa"], ['a', ItemID.crystal_matrix_ingot, 0, 'b', BlockID.dcompreBlock, 0]);
});


RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.skull_sword,
    count:1}, [
	"       ab",
	"      aba",
	"     aba ",
	"    aba  ",
	" c aba   ",
	"  cba    ",
	"  wc     ",
	" w  c    ",
	"s        "
	],{
	a:{id:ItemID.crystal_matrix_ingot}, b:{id:377}, c:{id:352}, w:{id:17}, s:{id:399}
	});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:BlockID.neutCo,
    count:1}, [
    "aabbbbbaa",
    "a bbbbb a",
	"a  ccc  a",
	"d ccccc d",
	"a ccdcc a",
	"d ccccc d",
	"a  ccc  a",
	"a       a",
	"aaadadaaa"
    ], {
    a:{id:42}, b:{id:155}, c:{id:152}, d:{id:ItemID.crystal_matrix_ingot}
});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.endestPearl,
    count:1}, [
	"   aaa   ",
	" aabbbaa ",
	" abbbbba ",
	"abbbcbbba",
	"abbcscbba",
	"abbbcbbba",
	" abbbbba ",
	" aabbbaa ",
	"   aaa   "
	],{
		a:{id:121}, b:{id:368}, c:{id:ItemID.ingotNeutronium}, s:{id:399}
	});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:BlockID.compressorAv,
    count:1}, [
    "aaabbbaaa",
    "c n   n c",
	"a n   n a",
	"c n   n c",
	"rnn g nnr",
	"c n   n c",
	"a n   n a",
	"c n   n c",
	"aaacacaaa"
    ], {
    a:{id:42}, b:{id:410}, c:{id:ItemID.crystal_matrix_ingot}, n:{id:ItemID.ingotNeutronium}, r:{id:152}, g:{id:BlockID.neutroniumBlock}
});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.cosmMeatballs,
    count:2}, [
	"abbccddee",
	"ff       ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "
	],{
		a:{id:ItemID.neutron_pile}, b:{id:363}, c:{id: 365}, d:{id:319}, f:{id:349}, e:{id:411}
	});
	
RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.ultimstew,
    count:1}, [
	"abbccddee",
	"ggffii   ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "
	],{
		a:{id:ItemID.neutron_pile}, b:{id:256}, c:{id:391}, d:{id:392}, e:{id:81}, g:{id:40}, f:{id:39}, i:{id:372}
	});
	
	RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.catalystInfinity,
    count:1}, [
	"aiglrqube",
	"f        ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "
	],{
		a:{id:133}, i:{id:ItemID.ironsing}, g:{id:ItemID.goldsing}, l:{id:ItemID.lapissing}, r:{id:ItemID.redstonesing}, q:{id:ItemID.quartzsing}, u:{id:ItemID.ultimstew}, b:{id:ItemID.cosmMeatballs}, e:{id:ItemID.endestPearl}, f:{id:ItemID.record_fragment}
	});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.ingotInfinity,
    count:1}, [
	"aaaaaaaaa",
	"abccbccba",
	"acbbcbbca",
	"abccbccba",
	"aaaaaaaaa",
	"         ",
	"         ",
	"         ",
	"         "
	],{
		a:{id:ItemID.ingotNeutronium}, b:{id:ItemID.crystal_matrix_ingot}, c:{id:ItemID.catalystInfinity}
	});
	
	
RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.cosmSword,
    count:1}, [
	"       aa",
	"      aaa",
	"     aaa ",
	"    aaa  ",
	" b aaa   ",
	"  baa    ",
	"  cb     ",
	" c  b    ",
	"d        "
	],{
	a:{id:ItemID.ingotInfinity}, b:{id:ItemID.crystal_matrix_ingot}, c:{id:ItemID.ingotNeutronium}, d:{id:ItemID.catalystInfinity}
	});

RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.cosmAxe,
    count:1}, [
	" a       ",
	"aaaaa    ",
	"aaaa     ",
	" ab      ",
	"  b      ",
	"  b      ",
	"  b      ",
	"  b      ",
	"  b      "
	],{
		a:{id:ItemID.ingotInfinity}, b:{id:ItemID.ingotNeutronium}
	});
	
RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.cosmShovel,
    count:1}, [
	"      aaa",
	"     aaba",
	"      aaa",
	"     c  a",
	"    c    ",
	"   c     ",
	"  c      ",
	" c       ",
	"c        "
	],{
		a:{id:ItemID.ingotInfinity}, b:{id:BlockID.infBlock}, c:{id:ItemID.ingotNeutronium}
	});
	
RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.infbow,
    count:1}, [
	"   aa    ",
	"  a b    ",
	" a  b    ",
	"a   b    ",
	"c   b    ",
	"a   b    ",
	" a  b    ",
	"  a b    ",
	"   aa    "
	],{
		a:{id:ItemID.ingotInfinity}, b:{id:35}, c:{id:BlockID.crystal_matrixAV}
	});
	
RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.cosmPickaxe,
    count:1}, [
	" aaaaaaa ",
	"aaaabaaaa",
	"aa  c  aa",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    "
	],{
		a:{id:ItemID.ingotInfinity}, b:{id:BlockID.crystal_matrixAV}, c:{id:ItemID.ingotNeutronium}
	});
	
	RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.cosmHoe,
    count:1}, [
	"     a   ",
	" bbbbbb  ",
	"bbbbbbb  ",
	"b    bb  ",
	"     a   ",
	"     a   ",
	"     a   ",
	"     a   ",
	"     a   "
	],{a:{id:ItemID.ingotInfinity}, b:{id:ItemID.ingotNeutronium}
		
	});
	
	RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.inf_helmet,
    count:1}, [
	"  aaaaa  ",
	" abbbbba ",
	" a cbc a ",
	" abbbbba ",
	" abbbbba ",
	" ab b ba ",
	"         ",
	"         ",
	"         "
	],{
		a:{id:ItemID.ingotNeutronium}, b:{id:ItemID.ingotInfinity}, c:{id:ItemID.catalystInfinity}
	});
	
	RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.inf_chestplate,
    count:1}, [
	" aa   aa ",
	"aaa   aaa",
	"aaa   aaa",
	" abbbbba ",
	" abbcbba ",
	" abbbbba ",
	" abbbbba ",
	" abbbbba ",
	"  aaaaa  "
	],{
		a:{id:ItemID.ingotNeutronium}, b:{id:ItemID.ingotInfinity}, c:{id:BlockID.crystal_matrixAV}
	});
	
	RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.inf_leggings,
    count:1}, [
	"aaaaaaaaa",
	"abbbcbbba",
	"abaacaaba",
	"aba   aba",
	"aea   aea",
	"aba   aba",
	"aba   aba",
	"aba   aba",
	"aba   aba"
	],{
		a:{id:ItemID.ingotNeutronium}, b:{id:ItemID.ingotInfinity}, c:{id:ItemID.catalystInfinity}, e:{id:BlockID.crystal_matrixAV}
	});
	
	RecipeTE.addShapeRecipe("extWorckbench", {
    id:ItemID.inf_boots,
    count:1}, [
	" aaa aaa ",
	" aba aba ",
	" aba aba ",
	"aaba abaa",
	"abba abba",
	"aaaa aaaa",
	"         ",
	"         ",
	"         "
	],{
		a:{id:ItemID.ingotNeutronium}, b:{id:ItemID.ingotInfinity}
	});
	
	




// file: TranslationAV.js

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




