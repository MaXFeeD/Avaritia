var inf_render = new Render(); 
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

