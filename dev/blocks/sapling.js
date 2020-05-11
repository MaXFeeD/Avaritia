IDRegistry.genBlockID("asgWhite");
Block.createBlock("asgWhite", [{
	name: "Asgardandelion",
	texture: [["empty", 0], ["empty", 0],
		["asgardandelion", 1]],
	inCreative: false
}]);

IDRegistry.genItemID("asgWhite");
Item.createItem("asgWhite", "Asgardandelion", {
	name: "asgardandelion"
});
SaplingRenderer.setOnBlock(BlockID.asgWhite, 0);

Item.registerUseFunction("asgWhite", function(coords, item, block) {
	var place = coords.relative;
	if (GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))) {
		World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
		Player.setCarriedItem(item.id, item.count - 1, item.data);
	}
});

Callback.addCallback("GenerateChunk", function(x, z) {
	for (var i = 0; i < 1; i++) {
		coords = GenerationUtils.randomCoords(x, z, 20, 96);
		for (var k = 20; k < 96; k++) {
			if (World.getBlockID(coords.x, k, coords.z) == 3) {
				if (World.getBlockID(coords.x, k + 1, coords.z) != 0) return;
				World.setBlock(coords.x, k + 1, coords.z, BlockID.asgWhite, 0);
			}
		}
	}
});

IDRegistry.genBlockID("solWhite");
Block.createBlock("solWhite", [{
	name: "Soarleander",
	texture: [["empty", 0], ["empty", 0],
		["soarleander", 1]],
	inCreative: false
}]);

IDRegistry.genItemID("solWhite");
Item.createItem("solWhite", "Soarleander", {
	name: "soarleander"
});
SaplingRenderer.setOnBlock(BlockID.solWhite, 0);

Item.registerUseFunction("solWhite", function(coords, item, block) {
	var place = coords.relative;
	if (GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))) {
		World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
		Player.setCarriedItem(item.id, item.count - 1, item.data);
	}
});

Callback.addCallback("GenerateChunk", function(x, z) {
	for (var i = 0; i < 1; i++) {
		coords = GenerationUtils.randomCoords(x, z, 20, 96);
		for (var k = 20; k < 96; k++) {
			if (World.getBlockID(coords.x, k, coords.z) == 3) {
				if (World.getBlockID(coords.x, k + 1, coords.z) != 0) return;
				World.setBlock(coords.x, k + 1, coords.z, BlockID.solWhite, 0);
			}
		}
	}
});
