var Renderer={
        setSaplingRender:function(id,x){
        var shape = new ICRender.CollisionShape();     
        BlockRenderer.setCustomCollisionShape(Block.getNumericId(id), -1, shape);    
        BlockRenderer.addRenderCallback(id, function(api, coords,block) {
            if(x!=0){
                for(var i = 0;i < 1/x;i+=x){
                api.renderBoxId(coords.x, coords.y, coords.z,0+i, 0.01, 0+i, x+i, 0.99, x+i,id, block.data);
                api.renderBoxId(coords.x, coords.y, coords.z,(1-x)-i, 0.01, 0+i,1-i, 0.99, x+i,id, block.data);
                }
            }
            else{
                api.renderBoxId(coords.x, coords.y, coords.z, 0.4999, 0.01, 0, 0.5, 0.99, 1,id, block.data);
                api.renderBoxId(coords.x, coords.y, coords.z, 0, 0.01, 0.4999, 1, 0.99, 0.5, id, block.data);
            }
        })
        BlockRenderer.enableCustomRender(id);
    }
};

/*IDRegistry.genBlockID("gaiaBlock"); 
Block.createBlock("gaiaBlock", [
    {name: "Gaia block", texture: [["block_gaia", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.gaiaBlock,3);
ToolAPI.registerBlockMaterial(BlockID.gaiaBlock, "stone", 4, true);
*/
IDRegistry.genBlockID("infBlock"); 
Block.createBlock("infBlock", [
    {name: "Infinity block", texture: [["infinity", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.infBlock,3);
ToolAPI.registerBlockMaterial(BlockID.infBlock, "stone", 4, true);

IDRegistry.genBlockID("neutroniumBlock"); 
Block.createBlock("neutroniumBlock", [
    {name: "Neutronium block", texture: [["neutronium", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.neutroniumBlock,3);
ToolAPI.registerBlockMaterial(BlockID.neutroniumBlock, "stone", 4, true);

IDRegistry.genBlockID("compreBlock"); 
Block.createBlock("compreBlock", [
    {name: "Compresed workbench", texture: [["compressed", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.compreBlock,4);
ToolAPI.registerBlockMaterial(BlockID.compreBlock, "wood", 0, true);

IDRegistry.genBlockID("dcompreBlock"); 
Block.createBlock("dcompreBlock", [
    {name: "Double compressed workbench", texture: [["double_compressed", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.dcompreBlock,6);
ToolAPI.registerBlockMaterial(BlockID.dcompreBlock, "wood", 2, true);

IDRegistry.genBlockID("extWorckbench");
Block.createBlockWithRotation("extWorckbench", [
 {name: "Extrеme workbench", texture:
 [["crafting", 2],
 ["craftingtop", 0],
 ["craftingside", 0],
  ["craftingside", 0],
   ["craftingside", 0],
    ["craftingside", 0]],
 inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.extWorckbench,4);
ToolAPI.registerBlockMaterial(BlockID.extWorckbench, "stone", 3, true);

IDRegistry.genBlockID("crystal_matrixAV");
Block.createBlockWithRotation("crystal_matrixAV", [
	{name: "Crystal matrix", texture: 
	[["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0],
	 ["crystal_matrix", 0]],
	      inCreative: true}
], "opaque");
Block.setDestroyTime(BlockID.crystal_matrixAV,4);
ToolAPI.registerBlockMaterial(BlockID.crystal_matrix, "stone", 4, true);

IDRegistry.genBlockID("compressorAv"); 
Block.createBlockWithRotation("compressorAv", [
    {name: "Compressor neutronium", texture: [["side", 0],["top_comp", 0],["side", 0],["compfront", 0],["side", 0],["side", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.compressorAv,4);
ToolAPI.registerBlockMaterial(BlockID.compressorAv, "stone", 4, true);

IDRegistry.genBlockID("neutCo"); 
Block.createBlockWithRotation("neutCo", [
    {name: "Neutroinium collector", texture: [["side", 0],["top", 0],["side", 0],["active", 0],["side", 0],["side", 0]],inCreative: true}], "opaque");
Block.setDestroyTime(BlockID.neutCo,4);
ToolAPI.registerBlockMaterial(BlockID.neutCo, "stone", 4, true);

/*
IDRegistry.genBlockID("asgWhite");
Block.createBlock("asgWhite", [
    {name: "Asgardandelion", texture: [["empty", 0], ["empty", 0], ["asgardandelion", 1]], inCreative: false}
]);

IDRegistry.genItemID("asgWhite");
Item.createItem("asgWhite", "Asgardandelion", {name: "asgardandelion"});

Item.registerUseFunction("asgWhite", function(coords, item, block){
    var place = coords.relative;
    if(GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))){
        World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
        Player.setCarriedItem(item.id, item.count - 1, item.data);  
    }
});

Renderer.setSaplingRender(BlockID.asgWhite,0);

Callback.addCallback("GenerateChunk", function(x,z){ 
for(var i = 0; i < 1; i++){ 
coords=GenerationUtils.randomCoords(x,z,20,96); 
for(var k=20;k<96;k++){ 
if(World.getBlockID(coords.x,k,coords.z)==3){ 
if(World.getBlockID(coords.x,k+1,coords.z)!=0)return 
World.setBlock(coords.x,k+1,coords.z,BlockID.Asgardandelion,0); 
} 
} 
} 
});

Recipes.addShaped({id: ItemID.gypMatter, count: 1, data: 0}, [
    "oao"  
], ['a', ItemID.asgWhite, 0]);


IDRegistry.genBlockID("solWhite");
Block.createBlock("solWhite", [
    {name: "Soarleander", texture: [["empty", 0], ["empty", 0], ["soarleander", 1]], inCreative: false}
]);

IDRegistry.genItemID("solWhite");
Item.createItem("solWhite", "Soarleander", {name: "soarleander"});

Item.registerUseFunction("solWhite", function(coords, item, block){
    var place = coords.relative;
    if(GenerationUtils.isTransparentBlock(World.getBlockID(place.x, place.y, place.z))){
        World.setBlock(place.x, place.y, place.z, BlockID.roseWhite);
        Player.setCarriedItem(item.id, item.count - 1, item.data);  
    }
});

Renderer.setSaplingRender(BlockID.solWhite,0);

Callback.addCallback("GenerateChunk", function(x,z){ 
for(var i = 0; i < 1; i++){ 
coords=GenerationUtils.randomCoords(x,z,20,96); 
for(var k=20;k<96;k++){ 
if(World.getBlockID(coords.x,k,coords.z)==3){ 
if(World.getBlockID(coords.x,k+1,coords.z)!=0)return 
World.setBlock(coords.x,k+1,coords.z,BlockID.solWhite,0); 
} 
} 
} 
});*/
