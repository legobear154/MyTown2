package mytown.commands.town.assistant;

import mytown.core.ChatUtils;
import mytown.core.utils.command.CommandBase;
import mytown.core.utils.command.Permission;
import mytown.datasource.MyTownDatasource;
import mytown.entities.Resident;
import mytown.proxies.DatasourceProxy;
import mytown.proxies.LocalizationProxy;
import net.minecraft.command.ICommandSender;

@Permission("mytown.cmd.assistant.plot.select.reset")
public class CmdPlotSelectReset extends CommandBase {

	public CmdPlotSelectReset(String name, CommandBase parent) {
		super(name, parent);
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return parent.canCommandSenderUseCommand(sender);
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		Resident res = getDatasource().getResident(sender.getCommandSenderName());
		res.resetSelection();

		ChatUtils.sendLocalizedChat(sender,LocalizationProxy.getLocalization(),"mytown.notification.town.plot.selectionReset");
	}

	/**
	 * Helper method to return the current MyTownDatasource instance
	 * 
	 * @return
	 */
	private MyTownDatasource getDatasource() {
		return DatasourceProxy.getDatasource();
	}
}
