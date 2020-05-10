var SaplingRenderer = {
	setOnBlock: function(id, offset) {
		var shape = new ICRender.CollisionShape();
		BlockRenderer.setCustomCollisionShape(Block.getNumericId(id), -1, shape);
		BlockRenderer.addRenderCallback(id, function(api, coords, block) {
			if (offset != undefined && offset != 0) {
				for (var i = 0; i < 1 / offset; i += offset) {
					api.renderBoxId(coords.x, coords.y, coords.z, 0 + i, 0.01, 0 + i, offset + i, 0.99, x + i, id, block.data);
					api.renderBoxId(coords.x, coords.y, coords.z, (1 - offset) - i, 0.01, 0 + i, 1 - i, 0.99, offset + i, id, block.data);
				}
			} else {
				api.renderBoxId(coords.x, coords.y, coords.z, 0.4999, 0.01, 0, 0.5, 0.99, 1, id, block.data);
				api.renderBoxId(coords.x, coords.y, coords.z, 0, 0.01, 0.4999, 1, 0.99, 0.5, id, block.data);
			}
		});
		BlockRenderer.enableCustomRender(id);
	}
};

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
