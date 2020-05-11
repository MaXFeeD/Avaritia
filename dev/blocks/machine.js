IDRegistry.genBlockID("compressorAv");
Block.createBlockWithRotation("compressorAv", [{
	name: "Compressor neutronium",
	texture: [["side", 0], ["top_comp", 0], ["side", 0],
				["compfront", 0], ["side", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.compressorAv, 4);
ToolAPI.registerBlockMaterial(BlockID.compressorAv, "stone", 4, true);

IDRegistry.genBlockID("neutCo"); 
Block.createBlockWithRotation("neutCo", [{
	name: "Neutroinium collector",
	texture: [["side", 0], ["top", 0], ["side", 0],
				["active", 0], ["side", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.neutCo, 4);
ToolAPI.registerBlockMaterial(BlockID.neutCo, "stone", 4, true);
