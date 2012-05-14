/*
	This file is part of the OdinMS Maple Story Server
    Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc>
		       Matthias Butz <matze@odinms.de>
		       Jan Christian Meyer <vimes@odinms.de>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.server.handlers.channel;

import constants.ServerConstants;
import client.MapleCharacter;
import client.MapleClient;
import net.AbstractMaplePacketHandler;
import tools.MaplePacketCreator;
import tools.data.input.SeekableLittleEndianAccessor;

public final class CharInfoRequestHandler extends AbstractMaplePacketHandler {
	@SuppressWarnings("unused")
	public final void handlePacket(SeekableLittleEndianAccessor slea, MapleClient c) {
		slea.readInt();
		int cid = slea.readInt();
		MapleCharacter player = (MapleCharacter) c.getPlayer().getMap().getMapObject(cid);
		if (player.isHidden() && !c.getPlayer().isGM())
			return;
		else if (player.isGM() && !c.getPlayer().isGM() && !ServerConstants.ALLOW_INFO_ON_GMS)
			return;
		c.announce(MaplePacketCreator.charInfo(player));
	}
}
