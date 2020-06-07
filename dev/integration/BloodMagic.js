/*
ModAPI.addAPICallback("BloodAPI", function(api){
});*/
 

IDRegistry.genItemID("orb_armok");
Item.createItem("orb_armok", "Bloos Orb of Armok", {name: "orb_armok"});

Item.registerIconOverrideFunction(ItemID.orb_armok, function(item, texture){
return {name: "orb_armok", meta: threeBlinkIndex};
});
setRequiresIconOverride(ItemID.orb_armok, true);

IDRegistry.genItemID("oreFractured");
Item.createItem("oreFractured", "Fractured ore", {
	name: "fractured_ore", meta: 0
});
