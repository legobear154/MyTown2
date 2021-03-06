package mytown.commands.admin;

import mytown.MyTown;
import mytown.core.ChatUtils;
import mytown.core.utils.command.CommandBase;
import mytown.core.utils.command.Permission;
import mytown.datasource.MyTownDatasource;
import mytown.entities.Rank;
import mytown.proxies.DatasourceProxy;
import mytown.proxies.LocalizationProxy;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

@Permission("mytown.adm.cmd.add")
public class CmdAdd extends CommandBase {

	public CmdAdd(String name, CommandBase parent) {
		super(name, parent);
	}

	@Override
	public void processCommand (ICommandSender sender, String[] args) {
		if (args.length < 2) throw new WrongUsageException(MyTown.getLocal().getLocalization("mytown.adm.cmd.usage.add"));
		if (!getDatasource().hasTown(args[1])) throw new CommandException(LocalizationProxy.getLocalization().getLocalization("mytown.cmd.err.town.notexist", args[1]));
		if (!getDatasource().hasResident(args[0])) throw new CommandException(LocalizationProxy.getLocalization().getLocalization("mytown.cmd.err.resident.notexist", args[0]));
		if (getDatasource().getTown(args[1]).hasResident(getDatasource().getResident(args[0]))) throw new CommandException(LocalizationProxy.getLocalization().getLocalization("mytown.adm.cmd.err.add.already", (Object[]) args));
		Rank rank;
		if(args.length > 2) {
			if(!getDatasource().getTown(args[1]).hasRankName(args[2])) throw new CommandException(LocalizationProxy.getLocalization().getLocalization("mytown.cmd.err.rank.notexist", args[1], args[2]));
			rank = getDatasource().getRank(args[2], getDatasource().getTown(args[1]));
		} else {
			rank = getDatasource().getRank("Resident", getDatasource().getTown(args[1]));
		}
		
		try {
			getDatasource().linkResidentToTown(getDatasource().getResident(args[0]), getDatasource().getTown(args[1]), rank);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ChatUtils.sendLocalizedChat(sender, MyTown.getLocal(), "mytown.notification.town.resident.add", (Object[]) args);
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
