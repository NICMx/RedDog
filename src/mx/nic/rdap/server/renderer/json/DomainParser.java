package mx.nic.rdap.server.renderer.json;

import java.net.IDN;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import mx.nic.rdap.core.db.Domain;
import mx.nic.rdap.server.PrivacyStatus;
import mx.nic.rdap.server.PrivacyUtil;

public class DomainParser {

	public static JsonArray getJsonArray(List<Domain> domains, boolean isAuthenticated, boolean isOwner) {
		JsonArrayBuilder builder = Json.createArrayBuilder();

		for (Domain domain : domains) {
			builder.add(getJson(domain, isAuthenticated, isOwner));
		}

		return builder.build();
	}

	public static JsonObject getJson(Domain domain, boolean isAuthenticated, boolean isOwner) {
		Map<String, PrivacyStatus> settings = PrivacyUtil.getDomainPrivacySettings();
		JsonObjectBuilder builder = Json.createObjectBuilder();

		builder.add("objectClassName", "domain");
		JsonUtil.fillCommonRdapJsonObject(builder, domain, isAuthenticated, isOwner, settings,
				PrivacyUtil.getDomainRemarkPrivacySettings(), PrivacyUtil.getDomainLinkPrivacySettings(),
				PrivacyUtil.getDomainEventPrivacySettings());

		String key = "ldhName";
		String value = domain.getLdhName();
		if (PrivacyUtil.isObjectVisible(value, key, settings.get(key), isAuthenticated, isOwner))
			builder.add(key, value);

		key = "unicodeName";
		value = IDN.toUnicode(domain.getLdhName());
		if (PrivacyUtil.isObjectVisible(value, key, settings.get(key), isAuthenticated, isOwner))
			builder.add(key, value);

		key = "variants";
		if (PrivacyUtil.isObjectVisible(domain.getVariants(), key, settings.get(key), isAuthenticated, isOwner))
			builder.add(key, VariantParser.getJsonArray(domain.getVariants(), isAuthenticated, isOwner));

		key = "publicIds";
		if (PrivacyUtil.isObjectVisible(domain.getPublicIds(), key, settings.get(key), isAuthenticated, isOwner)) {
			builder.add(key, PublicIdParser.getJsonArray(domain.getPublicIds(), isAuthenticated, isOwner,
					PrivacyUtil.getDomainPublicIdsPrivacySettings()));
		}

		key = "nameservers";
		if (PrivacyUtil.isObjectVisible(domain.getNameServers(), key, settings.get(key), isAuthenticated, isOwner))
			builder.add(key, NameserverParser.getJsonArray(domain.getNameServers(), isAuthenticated, isOwner));

		key = "secureDNS";
		if (PrivacyUtil.isObjectVisible(domain.getSecureDNS(), key, settings.get(key), isAuthenticated, isOwner))
			builder.add(key, SecureDNSParser.getJsonObject(domain.getSecureDNS(), isAuthenticated, isOwner));

		return builder.build();
	}

}
