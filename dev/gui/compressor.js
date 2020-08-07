var compressorGUI = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: Translation.translate("Compressor")
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
		x: 584, y: 200,
		scale: 3.6,
		bitmap: "progress_background"
	}],
	elements: {
		intext: {
			type: Translation.translate("text"),
			x: 452, y: 160,
			width: 68,
			height: 16,
			text: "Input",
			font:{alignment: 1}
		},
		input: {
			type: "slot",
			x: 422, y: 192,
			size: 68,
			visual: true,
			needClean: true,
			isTransparentBackground: true,
			bitmap: 'ЖекаСукаИсправьУжеНастройкуПрозрачностиСлотов'
		},
		slot_0: {
			type: "slot",
			x: 510, y: 192,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		progress: {
			type: "scale",
			x: 584, y: 200,
			direction: 0,
			bitmap: "progress",
			scale: 3.6,
			value: 1
		},
		sing: {
			type: "scale",
			x: 667, y: 190,
			direction: 0,
			bitmap: "sing",
			scale: 2.3,
			value: 1
		},
		slot_1: {
			type: "slot",
			x: 750, y: 192,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		count: {
			type: "text",
			x: 660, y: 272,
			width: 120,
			height: 16,
			text: "0/0",
			font:{alignment: 1}
		},
		outext: {
			type: Translation.translate("text"),
			x: 878, y: 160,
			width: 68,
			height: 16,
			text: "Output",
			font:{alignment: 1}
		},
		output: {
			type: "slot",
			x: 838, y: 192,
			size: 68,
			visual: true,
			needClean: true,
			isTransparentBackground: true,
			bitmap: 'ЖекаСукаИсправьУжеНастройкуПрозрачностиСлотов'
		},
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
});
Compressor.addRecipe(41, {
	count: 650,
	out: ItemID.goldsing,
});
Compressor.addRecipe(155, {
	count: 750,
	out: ItemID.quartzsing,
});
Compressor.addRecipe(152, {
	count: 950,
	out: ItemID.redstonesing,
});
Compressor.addRecipe(22, {
	count: 850,
	out: ItemID.lapissing,
});

TileEntity.registerPrototype(BlockID.compressorAv,{
	defaultValues:{
		count:0,
		progress:0,
		end:0,
		id:0,
		result: null
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
				this.data.count += 1;
				this.data.end = recipe.count; 
				this.data.progress += 1/this.data.end;
				slot0.count--;
				this.data.result = recipe.out;
				this.container.setSlot('output', this.data.result, 1, 0);
				}
				if(this.data.progress >= 1){
					slot1.id = recipe.out;
					slot1.count++;
					this.data.count -= this.data.end;
					this.data.id = 0;
					this.data.progress = 0;
					}
				}
				this.container.setSlot('input', this.data.id, 1, 0);
				this.container.setText("count",this.data.count +" / "+this.data.end);
				this.container.setScale("progress",this.data.progress);
				this.container.validateAll();
			},
	getGuiScreen:function(){
		return compressorGUI;
		}
	});
  
 

