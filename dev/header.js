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

IMPORT("RecipeTileEntityLib");
IMPORT("ToolType");
IMPORT("SoundAPI");
IMPORT("Bow");

//ModAPI.registerAPI("AvaritiaAPI", какойто объект хз чо);

var isHorizon = getCoreAPILevel() > 8;

Item.setRequiresIconOverride = ModAPI.requireGlobal("Item.setRequiresIconOverride");
if (!isHorizon) Game.getGameMode = ModAPI.requireGlobal("Level.getGameMode");
var EntityDataRegistry = ModAPI.requireGlobal("EntityDataRegistry");

var ArmorTick = {
	attachTo: function(arg) {
		if (!arg.tick || arg.id == undefined || arg.type == undefined) return;
		Callback.addCallback("tick", function() {
			if (Player.getArmorSlot(arg.type).id == arg.id) arg.tick();
		});
	}
};

function makeReplaceable(item, id, replacement) {
	if (item == undefined || typeof item != "object" || typeof item.id != "number") return;
	if (item.id == id) Player.setCarriedItem(replacement, item.count, item.data, item.extra);
	else if (item.id == replacement) Player.setCarriedItem(id, item.count, item.data, item.extra);
}

function addNamedCallback(action, current) {
	Callback.addCallback(current, function() {
		var arguments = Array.prototype.slice.call(arguments);
		(arguments.unshift(current), action.apply(Callback, arguments));
	});
}

function makeSimplifiedCallback(action) {
	var arguments = Array.prototype.slice.call(arguments);
	if (arguments.length == 0) return Logger.Log("can't create makeSimplifiedCallback without action & names", "ERROR");
	if (arguments.length == 1) return Logger.Log("no names specified for makeSimplifiedCallback", "ERROR");
	arguments.shift(); // removing action from callback names, it attached into function
	arguments.forEach(function(current) { addNamedCallback(action, current); });
}
