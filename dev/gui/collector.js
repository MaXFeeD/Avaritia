const CollectorProgress = Translation.translate("Progress: ");

var guiCollector = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: Translation.translate("Collector")
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: new Array(),
	elements: {
		output: {
			type: "slot",
			x: 570,
			y: 160,
			size: 102
		},
		progress: {
			type: "text",
			x: 625,
			y: 290,
			width: 120,
			height: 16,
			font: {
				alignment: 1
			}
		}
	}
});

var COLLECTOR_TICKS = 6000;

TileEntity.registerPrototype(BlockID.neutCo, {
	defaultValues: {
		progress: 0
	},
	getGuiScreen: function() {
		return guiCollector;
	},
	tick: function() {
		slot = this.container.getSlot("output");
		this.container.setText("progress", CollectorProgress + parseInt(this.data.progress / COLLECTOR_TICKS * 100) + "%");
		if (++this.data.progress >= COLLECTOR_TICKS && slot.count < 64) {
			if (slot.id == ItemID.neutron_pile) {
				slot.count ++;
				this.data.progress = 0;
			} else if (slot.id == 0 && !slot.count) {
				slot.id = ItemID.neutron_pile;
			} else this.data.progress = COLLECTOR_MAX;
		}
	}
});
