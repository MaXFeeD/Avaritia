IDRegistry.genBlockID("infBlock");
Block.createBlock("infBlock", [{
	name: "Infinity Block",
	texture: [["infinity", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.infBlock, 3);
ToolAPI.registerBlockMaterial(BlockID.infBlock, "stone", 4, true);

IDRegistry.genBlockID("neutroniumBlock");
Block.createBlock("neutroniumBlock", [{
	name: "Neutronium Block",
	texture: [["neutronium", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.neutroniumBlock, 3);
ToolAPI.registerBlockMaterial(BlockID.neutroniumBlock, "stone", 4, true);

IDRegistry.genBlockID("crystal_matrixAV");
Block.createBlock("crystal_matrixAV", [{
	name: "Crystal Matrix",
	texture: [["crystal_matrix", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.crystal_matrixAV, 4);
ToolAPI.registerBlockMaterial(BlockID.crystal_matrix, "stone", 4, true);
