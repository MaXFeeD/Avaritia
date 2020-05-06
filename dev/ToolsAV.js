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
