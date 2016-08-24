package mx.nic.rdap.result;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

import mx.nic.rdap.RdapResult;

public class SampleResult implements RdapResult {

	private String domain;
	
	public SampleResult(String domain) {
		this.domain = domain;
	}
	
	@Override
	public JsonObject toJson() {
		return Json.createObjectBuilder() //
				.add("domain", domain) //
				.add("foo", JsonValue.FALSE) //
				.build();
	}

}