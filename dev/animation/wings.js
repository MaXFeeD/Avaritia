/*var inf_render = new Render(); 
    var partObj = [ 
        {
            type: "box",
            coords: {
                x: 0,
                y: 31,
                z: 0
            },
            size: {
                x: 64,
                y: 64,
                z: 0
            },
            uv: {
                x: 0,
                y: 0
            }
        }];
inf_render.setPart("body", partObj, {});

let isFly = false;
Callback.addCallback("tick", function() {
 if (Player.getArmorSlot(1).id == ItemID.inf_chestplate && Player.getFlying(true)) {
	 if(isFly){
		 Entity.setRender(Player.get(), inf_render);
		}
		isFly;
    } else if (Player.getArmorSlot(1).id != ItemID.inf_chestplate || Player.getFlying(false)) {
		Entity.setRender(Player.get(), 30);
		!isFly;
	}
});



const VertexForWings = [[0,0,0,0,0],[0,0,2,32,0],[0,-2,0,0,32],[0,-2,2,32,32],[0,0,2,32,0],[0,-2,0,0,32]];
const lwing = new RenderMesh(), rwing = new RenderMesh(), lglow = new RenderMesh(), rglow = new RenderMesh();
const arender = new ActorRenderer();
//const arenderDop = new ActorRenderer().setTexture('armor/infinity_armor_wingglow.png');
for(let i in VertexForWings){
		lwing.addVertex(VertexForWings[i][0], VertexForWings[i][1], VertexForWings[i][2], VertexForWings[i][3], VertexForWings[i][4]);
		lglow.addVertex(VertexForWings[i][0], VertexForWings[i][1], VertexForWings[i][2], VertexForWings[i][3], VertexForWings[i][4]);
		rwing.addVertex(VertexForWings[i][0], VertexForWings[i][1], VertexForWings[i][2], VertexForWings[i][3], VertexForWings[i][4]);
		rglow.addVertex(VertexForWings[i][0], VertexForWings[i][1], VertexForWings[i][2], VertexForWings[i][3], VertexForWings[i][4]);
	}
	
	lwing.rotate(0,1,0,0,-1*Math.PI/180*75,0);
	rwing.rotate(0,1,0,0,1*Math.PI/180*75,0);
	rglow.rotate(0,1,0,0,1*Math.PI/180*75,0);
	lglow.rotate(0,1,0,0,-1*Math.PI/180*75,0);
	
	*/
