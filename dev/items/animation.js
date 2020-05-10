var fiv = 0;
Item.registerIconOverrideFunction(ItemID.ironsing, function(item, texture){
return {name: "singularity_iron", meta: fiv};
});
Item.setRequiresIconOverride(ItemID.ironsing, true);

Item.registerIconOverrideFunction(ItemID.goldsing, function(item, texture){
return {name: "singularity_gold", meta: fiv};
});
Item.setRequiresIconOverride(ItemID.goldsing, true);

Item.registerIconOverrideFunction(ItemID.lapissing, function(item, texture){
return {name: "singularity_lapis", meta: fiv};
});
Item.setRequiresIconOverride(ItemID.lapissing, true);

Item.registerIconOverrideFunction(ItemID.quartzsing, function(item, texture){
return {name: "singularity_quartz", meta: fiv};
});
Item.setRequiresIconOverride(ItemID.quartzsing, true);

Item.registerIconOverrideFunction(ItemID.redstonesing, function(item, texture){
return {name: "singularity_redstone", meta: fiv};
});
Item.setRequiresIconOverride(ItemID.redstonesing, true);

var fou = 0;
Item.registerIconOverrideFunction(ItemID.endestPearl, function(item, texture){
return {name: "endest", meta: fou};
});
Item.setRequiresIconOverride(ItemID.endestPearl, true);

var sev = 0;
Item.registerIconOverrideFunction(ItemID.cosmMeatballs, function(item, texture){
return {name: "cosm_meatballs", meta: sev};
});
Item.setRequiresIconOverride(ItemID.cosmMeatballs, true);
/* var Animate = function(IDD,M,f){ 
var FtP = World.getTime / f; 
var ind = 0;
if(FtP > 0){ 
Item.registerIconOverrideFunction(IDD, function(item, name){ 
return {name: name, meta: M+1}
 }); 
 } 
};
Animate(ItemID.cosmMeatballs, 0, 7); */

var eit = 0;
Item.registerIconOverrideFunction(ItemID.inf_chestplate, function(item, texture){
return {name: "chestaplateAV", meta: eit};
});
Item.setRequiresIconOverride(ItemID.inf_chestplate, true);

Item.registerIconOverrideFunction(ItemID.cosmhammer, function(item, texture){
return {name: "infhammer", meta: eit};
});
Item.setRequiresIconOverride(ItemID.cosmhammer, true);

Item.registerIconOverrideFunction(ItemID.cosmHoe, function(item, texture){
return {name: "infhoe", meta: eit};
});
Item.setRequiresIconOverride(ItemID.cosmHoe, true);

Item.registerIconOverrideFunction(ItemID.catalystInfinity, function(item, texture){
return {name: "infinity_catalyst", meta: eit};
});
Item.setRequiresIconOverride(ItemID.catalystInfinity, true);

Item.registerIconOverrideFunction(ItemID.ingotInfinity, function(item, texture){
return {name: "infinity_ingot", meta: eit};
});
Item.setRequiresIconOverride(ItemID.ingotInfinity, true);

Item.registerIconOverrideFunction(ItemID.cosmPickaxe, function(item, texture){
return {name: "infpickaxe", meta: eit};
});
Item.setRequiresIconOverride(ItemID.cosmPickaxe, true);

Item.registerIconOverrideFunction(ItemID.cosmShovel, function(item, texture){
return {name: "infshovel", meta: eit};
});
Item.setRequiresIconOverride(ItemID.cosmShovel, true);

Item.registerIconOverrideFunction(ItemID.cosmSword, function(item, texture){
return {name: "cosm_sword", meta: eit};
});
Item.setRequiresIconOverride(ItemID.cosmSword, true);

Item.registerIconOverrideFunction(ItemID.inf_leggings, function(item, texture){
return {name: "legginsAV", meta: eit};
});
Item.setRequiresIconOverride(ItemID.inf_leggings, true);

var tre = 0;
Item.registerIconOverrideFunction(ItemID.skull_sword, function(item, texture){
return {name: "skull_sword", meta: tre};
});
Item.setRequiresIconOverride(ItemID.skull_sword, true);

/* Item.registerIconOverrideFunction(ItemID.orb_armok, function(item, texture){
return {name: "orb_armok", meta: tre};
});
Item.setRequiresIconOverride(ItemID.orb_armok, true); */

var two = 0;
Item.registerIconOverrideFunction(ItemID.ingotNeutronium, function(item, texture){
return {name: "neutronium_ingot", meta: two};
});
Item.setRequiresIconOverride(ItemID.ingotNeutronium, true);

var ts = 0;
Item.registerIconOverrideFunction(ItemID.ultimstew, function(item, texture){
return {name: "ultimstew", meta: ts};
});
Item.setRequiresIconOverride(ItemID.ultimstew, true);

Callback.addCallback("tick", function(){
	var time = World.getThreadTime() % 4;
	if(time == 0 || time == 4) {
		if(two < 2) two++;
		else two = 0;
		if(tre < 3) tre++;
		else tre = 0;
		if(fou < 4) fou++;
		else fou = 0;
		if(fiv < 5) fiv++;
		else fiv = 0;
		if(sev < 7) sev++;		
		else sev = 0;
		if(eit < 8) eit++;
		else eit = 0;
		if(ts < 27) ts++;
		else ts = 0;
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
var wing = new Animation.Base(coords.x, coords.y + 1, coords.z);
// wing.setInterpolationEnabled(true);
wing.describe({ render: inf_render.getId(),
skin: "armor/infinity_armor_wing_full.png" });

wing.loadCustom(function() {
	var coords = Player.getPosition();
	this.setPos(coords.x, coords.y + 1, coords.z); 
    this.refresh();
});
