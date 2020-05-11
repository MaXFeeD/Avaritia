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
