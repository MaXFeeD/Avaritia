var compressorGUI = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Compressor"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [{
		type:"bitmap",
		x: 574, y: 238,
		scale: 3.6,
		bitmap: "progress_background"
	}],
	elements: {
		slot_0: {
			type: "slot",
			x: 500, y: 230,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		progress: {
			type: "scale",
			x: 574, y: 238,
			direction: 0,
			bitmap: "progress",
			scale: 3.6,
			value: 1
		},
		sing: {
			type: "scale",
			x: 657, y: 228,
			direction: 0,
			bitmap: "sing",
			scale: 2.3,
			value: 1
		},
		slot_1: {
			type: "slot",
			x: 740, y: 230,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		count: {
			type: "text",
			x: 580, y: 310,
			width: 120,
			height: 16,
			text: "0/0"
		},
		block: {
			type: "text",
			x: 580, y: 360,
			width: 120,
			height: 16,
			text: ""
		}
	}
});

var Compressor = {
	recipe:{},
	addRecipe:function(a,b){
		this.recipe[a] = b;
		},
		getRecipe: function (a){
			if(this.recipe[a]){
				return this.recipe[a];
				}
		},
	};

Compressor.addRecipe(42, {
	count: 850,
	out: ItemID.ironsing,
	name: "Iron"
});
Compressor.addRecipe(41, {
	count: 650,
	out: ItemID.goldsing,
	name: "Gold"
});
Compressor.addRecipe(155, {
	count: 750,
	out: ItemID.quartzsing,
	name: "Quartz"
});
Compressor.addRecipe(152, {
	count: 950,
	out: ItemID.redstonesing,
	name: "Redstone"
});
Compressor.addRecipe(22, {
	count: 850,
	out: ItemID.lapissing,
	name: "Lapis"
});

TileEntity.registerPrototype(BlockID.compressorAv,{
	defaultValues:{
		count:0,
		progress:0,
		end:0,
		id:0,
		block: "",
		},
		tick:function(){
			var slot0 = this.container.getSlot("slot_0");
			var slot1 = this.container.getSlot("slot_1");
			var recipe = Compressor.getRecipe(slot0.id);
			if(recipe){
			if(this.data.id==0&&slot0.id!=0){
				this.data.id = slot0.id;
				}
			if(slot0.id==this.data.id&&slot0.id!=0){
				this.data.block = recipe.name;
				this.data.count += 1;
				this.data.end = recipe.count; 
				this.data.progress += 1/this.data.end;
				slot0.count--;
				}
				if(this.data.progress >= 1){
					slot1.id = recipe.out;
					slot1.count++;
					this.data.count -= this.data.end;
					this.data.id = 0;
					this.data.progress = 0;
					}
				}
				this.container.setText("block", "Block of " + this.data.block);
				this.container.setText("count",this.data.count +" / "+this.data.end);
				this.container.setScale("progress",this.data.progress);
				this.container.validateAll();
			},
	getGuiScreen:function(){
		return compressorGUI;
		}
	});
  
 

