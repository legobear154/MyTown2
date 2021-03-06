package mytown.proxies.mod;

import java.util.ArrayList;
import java.util.List;

import mytown.MyTown;
import mytown.proxies.mod.MyTownChat.MyTownChatModProxy;

// TODO Optimize getting the enabled state of each proxy?

public class ModProxies {
	private static List<ModProxy> proxies = new ArrayList<ModProxy>();

	private ModProxies() {
	}

	/**
	 * Adds all the {@link ModProxy}'s to the list
	 */
	public static void addProxies() {
		ModProxies.addProxy(new MyTownChatModProxy());
	}

	/**
	 * Adds the given {@link ModProxy}
	 * 
	 * @param proxy
	 */
	public static void addProxy(ModProxy proxy) {
		ModProxies.proxies.add(proxy);
	}

	/**
	 * Removes the given {@link ModProxy}
	 * 
	 * @param proxy
	 */
	public static void removeProxy(ModProxy proxy) {
		ModProxies.proxies.remove(proxy);
	}

	public static void preInit() {
		for (ModProxy p : ModProxies.proxies) {
			if (!MyTown.instance.config.get("ModProxies", p.getName(), true).getBoolean(true)) {
				continue;
			}
			p.preInit();
		}
	}

	public static void init() {
		for (ModProxy p : ModProxies.proxies) {
			if (!MyTown.instance.config.get("ModProxies", p.getName(), true).getBoolean(true)) {
				continue;
			}
			p.init();
		}
	}

	public static void postInit() {
		for (ModProxy p : ModProxies.proxies) {
			if (!MyTown.instance.config.get("ModProxies", p.getName(), true).getBoolean(true)) {
				continue;
			}
			p.postInit();
		}
	}
}