IMPORT("ToolType");
IMPORT("SoundAPI");

/*
 ▄▄▄          ██▒   █▓    ▄▄▄          ██▀███      ██▓   ▄▄▄█████▓    ██▓    ▄▄▄      
▒████▄       ▓██░   █▒   ▒████▄       ▓██ ▒ ██▒   ▓██▒   ▓  ██▒ ▓▒   ▓██▒   ▒████▄    
▒██  ▀█▄      ▓██  █▒░   ▒██  ▀█▄     ▓██ ░▄█ ▒   ▒██▒   ▒ ▓██░ ▒░   ▒██▒   ▒██  ▀█▄  
░██▄▄▄▄██      ▒██ █░░   ░██▄▄▄▄██    ▒██▀▀█▄     ░██░   ░ ▓██▓ ░    ░██░   ░██▄▄▄▄██ 
 ▓█   ▓██▒      ▒▀█░      ▓█   ▓██▒   ░██▓ ▒██▒   ░██░     ▒██▒ ░    ░██░    ▓█   ▓██▒
 ▒▒   ▓▒█░      ░ ▐░      ▒▒   ▓▒█░   ░ ▒▓ ░▒▓░   ░▓       ▒ ░░      ░▓      ▒▒   ▓▒█░
  ▒   ▒▒ ░      ░ ░░       ▒   ▒▒ ░     ░▒ ░ ▒░    ▒ ░       ░        ▒ ░     ▒   ▒▒ ░
  ░   ▒           ░░       ░   ▒        ░░   ░     ▒ ░     ░          ▒ ░     ░   ▒   
      ░  ░         ░           ░  ░      ░         ░                  ░           ░  ░
                  ░                                                                   
*/


var BHMesh = new RenderMesh(__dir__ + "models/blackhole.obj", "obj", {
scale:[0.1,0.1,0.1],
translate:[0,-1.2,0]
});
var BHRender = new Render();
var BHPart = BHRender.getPart("head"); 
BHPart.setMesh(BHMesh);
var BHtexture= new Texture("mob/BHtexture.png").setResolution(256, 256);

var ModelBH = new EntityModel();
ModelBH.setRender(BHRender);
ModelBH.setTexture(BHtexture);

var BlackHole = MobRegistry.registerEntity("BlackHole");
BlackHole.customizeVisual({
    getModels: function() {
        return {
            "main": ModelBH
        };
    }
});

BlackHole.customizeDescription({
    getHitbox: function() {
        return {
            w: 0,
            h: 0
        };
    }
});

IDRegistry.genItemID("customEntitySpawn"); 
Item.createItem("customEntitySpawn", "Spawn custom entity", {name: "stick"});
Item.registerUseFunction("customEntitySpawn", function(coords, item, block){
    Entity.spawnCustom("BlackHole", coords.relative.x + .5, coords.relative.y + .5, coords.relative.z + .5); 
});


IDRegistry.genItemID("crystal_matrix_ingot");
Item.createItem("crystal_matrix_ingot", "Crystal matrix ingot", {name: "crystal_matrix_ingot"});

IDRegistry.genItemID("diamond_lattice");
Item.createItem("diamond_lattice", "Diamond lattice", {name: "diamond_lattice"});

IDRegistry.genItemID("neutron_pile");
Item.createItem("neutron_pile", "Neutronium pile", {name: "neutron_pile"});

IDRegistry.genItemID("neutron_nugget");
Item.createItem("neutron_nugget", "Neutronium nugget", {name: "neutron_nugget"});

IDRegistry.genItemID("ingotNeutronium");
Item.createItem("ingotNeutronium", "Neutronium ingot", {name: "neutronium_ingot"});

IDRegistry.genItemID("catalystInfinity");
Item.createItem("catalystInfinity", "Infinity catalyst", {name: "infinity_catalyst"});

IDRegistry.genItemID("ingotInfinity");
Item.createItem("ingotInfinity", "Infinity ingot", {name: "infinity_ingot"});

IDRegistry.genItemID("record_fragment");
Item.createItem("record_fragment", "Record Fragment", {name: "record_fragment"});

IDRegistry.genItemID("ironsing");
Item.createItem("ironsing", "Iron Singularity", {name: "singularity_iron"});

IDRegistry.genItemID("goldsing");
Item.createItem("goldsing", "Gold Singularity", {name: "singularity_gold"});

IDRegistry.genItemID("lapissing");
Item.createItem("lapissing", "Lapis Singularity", {name: "singularity_lapis"});

IDRegistry.genItemID("redstonesing");
Item.createItem("redstonesing", "Redstone Singularity", {name: "singularity_redstone"});

IDRegistry.genItemID("quartzsing");
Item.createItem("quartzsing", "Quartz Singularity", {name: "singularity_quartz"});

/*IDRegistry.genItemID("oreFractured");
Item.createItem("oreFractured", "Fractured ore", {name: "fractured_ore"});
IDRegistry.genItemID("infChunck");
Item.createItem("infChunck", "Infinity chunck", {name: "resource_infinity_drop"});

*/

IDRegistry.genItemID("gypMatter");
Item.createItem("gypMatter", "Ghyper Matter", {name: "gypmatter"});

IDRegistry.genItemID("endestPearl");
Item.createThrowableItem("endestPearl", "Endest pearl", {name: "endest"}, {stack: 16});


var endparticle = Particles.registerParticleType({
  texture: "ep",
  size: [1, 1],
  lifetime: [40, 40],
  render: 2,
  velocity: [0, -0.1, 0]
});

	
function handleExplode(coords, time) {
 Threading.initThread("explode", function() {
  java.lang.Thread.sleep(time * 50);
  Entity.remove("BlackHole");
  World.explode(coords.x, coords.y, coords.z, 7, false);
 }, 0);
}


Callback.addCallback('ProjectileHit', function (projectile, item, target, coords) {
	Player.setCarriedItem(item.id, item.count - 1, 0);
    coords = Entity.getPosition(projectile);
    let fmobs = Entity.getAllInRange(coords, 8);
Entity.spawnCustom("BlackHole", coords.x, coords.y, coords.z); 
for(var i in fmobs){
    Entity.moveToTarget(fmobs[i], coords.x, coords.y, coords.z, {speed: 1, denyY: false, jumpVel: 0});
}
    for (var dx = -5; dx < 5; dx++) {
    for (var dy = -3; dy < 5; dy++) {
    for (var dz = -5; dz < 5; dz++) {	
         if(dx * dx + dy * dy + dz * dz <= 20){
	     Particles.addParticle(endparticle, coords.x+0.5 + dx, coords.y+1 + dy, coords.z+0.5 + dz, 0, -0.1, 0, 1);	
	}}}
};
     handleExplode(coords, 20 * 9);
});


var claster = new ItemExtraData();

var DroppedItems = [];
Callback.addCallback("EntityAdded", function(entity){
if(Entity.getType(entity) == Native.EntityType.ITEM) DroppedItems.push(entity);
});
