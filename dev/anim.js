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

