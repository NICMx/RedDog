package mx.nic.rdap.server.result;

import javax.json.JsonObject;

import mx.nic.rdap.core.db.IpNetwork;
import mx.nic.rdap.server.RdapResult;
import mx.nic.rdap.server.UserRequestInfo;
import mx.nic.rdap.server.renderer.json.IpNetworkParser;

public class IpResult extends UserRequestInfo implements RdapResult {

	private IpNetwork ipNetwork;

	public IpResult(IpNetwork ipNetwork, String userName) {
		this.ipNetwork = ipNetwork;
		setUserName(userName);
	}

	@Override
	public JsonObject toJson() {
		return IpNetworkParser.getJson(ipNetwork, isUserAuthenticated(), isOwner(ipNetwork));
	}

}
