let fiveBlinkIndex = 0;
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

Item.registerIconOverrideFunction(ItemID.diamondsing, function(item, texture) {
	return { name: "singularity_diamond", meta: fiveBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.diamondsing, true);

Item.registerIconOverrideFunction(ItemID.emeraldsing, function(item, texture) {
	return { name: "singularity_emerald", meta: fiveBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.emeraldsing, true);

let fourBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.endestPearl, function(item, texture) {
	return { name: "endest", meta: fourBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.endestPearl, true);

let sevenBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.cosmMeatballs, function(item, texture) {
	return { name: "cosm_meatballs", meta: sevenBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.cosmMeatballs, true);

let eigthBlinkIndex = 0;
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

let threeBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.skull_sword, function(item, texture) {
	return { name: "skull_sword", meta: threeBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.skull_sword, true);

Item.registerIconOverrideFunction(ItemID.orb_armok, function(item, texture){
	return { name: "orb_armok", meta: threeBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.orb_armok, true);

let twoBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.ingotNeutronium, function(item, texture){
	return { name: "neutronium_ingot", meta: twoBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.ingotNeutronium, true);

let longestBlinkIndex = 0;
Item.registerIconOverrideFunction(ItemID.ultimstew, function(item, texture){
	return { name: "ultimstew", meta: longestBlinkIndex };
});
Item.setRequiresIconOverride(ItemID.ultimstew, true);


let time = World.getThreadTime() % 500;//25 sec
Callback.addCallback("tick", function(){
	if (time == 0 || time == 10) {
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

