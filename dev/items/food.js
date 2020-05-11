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
