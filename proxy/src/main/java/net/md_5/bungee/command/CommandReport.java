package net.md_5.bungee.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandReport extends Command
{

    public CommandReport()
    {
        super( "report", "dionn.command.report" );
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
		//laten we checken of er wel genoeg arguments worden opgegeven!
        if ( args.length < 2 )
        {
            sender.sendMessage( ChatColor.RED + "Er ontbreekt een naam of reden bij je report." );
            sender.sendMessage( ChatColor.RED + "Correct gebruik: /report <naam> <reden>" );
        } else
        {
			//de speler even opvragen
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer( args[0] );

			//checken of de speler wel online is
            if ( player == null || player.getServer() == null )
            {
                sender.sendMessage( ChatColor.GREEN + "[NexusReport] " + ChatColor.RED + "Deze speler is niet eens online!" );
            } 
			
			else //alles klopt, we versturen de report!
            {
				//even zeggen dat de report is verstuurd!
				sender.sendMessage( ChatColor.GREEN + "[NexusReport] " + ChatColor.GOLD + "Bedankt! Je report is verzonden." ); 
				
				//de reden ophalen
				StringBuilder reden = new StringBuilder();
					for(int i = 1; i < args.length; i++)
					{
						reden.append(args[ i ]);
						reden.append(" ");
					}

				for (ProxiedPlayer staffplayer : ProxyServer.getInstance().getPlayers()) {
						if (staffplayer.hasPermission("dionn.getreport")){
						//dan versturen we 'm nu naar de mods
						staffplayer.sendMessage( ChatColor.AQUA + "===================== REPORT =====================" );
						staffplayer.sendMessage( ChatColor.GREEN + "Verstuurd door: " + ChatColor.GOLD + sender.getName() );
						staffplayer.sendMessage( ChatColor.GREEN + "Speler: " + ChatColor.GOLD + args[0] + " op server " + player.getServer().getInfo().getName() );
						staffplayer.sendMessage( ChatColor.GREEN + "Reden: " + ChatColor.GOLD + reden );
						staffplayer.sendMessage( ChatColor.AQUA + "===================== REPORT =====================" );
						}							
					}
				
            }
        }
    }
}
