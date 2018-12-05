package br.com.portoseguro.crypter.controller;

import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.portoseguro.crypter.util.CryptUtil;
import br.com.portoseguro.crypter.util.HashMapCache;
import br.com.portoseguro.crypter.util.RandomString;

@RestController
@RequestMapping("/api/token")
@ConfigurationProperties(prefix="token")

public class TokenController {
	
	@RequestMapping(
		method=RequestMethod.POST, 
		value="/create", 
		consumes="application/json", 
		produces="application/json")
	public @ResponseBody String	encrypt(@RequestBody Map<String, Object> validationData) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			if (validationData != null && !validationData.isEmpty()) {
				JSONObject body = new JSONObject();
				JSONObject info = new JSONObject();
				info.put("date", new Date());
				body.put("info", info);
				JSONObject data = new JSONObject();
				for (String key : validationData.keySet()) {
					data.put(key, validationData.get(key));
				}
				body.put("data", data);
				String randomString = getToken(body);
				json.put("token", randomString);
			} else {
				json.put("error", "empty data!");
			}
		} catch (Exception e) {
			json.put("error", e.getMessage());
		}
		return json.toString();
	}
	
	@RequestMapping(
		method=RequestMethod.POST, 
		value="/get", 
		consumes = "application/json", 
		produces="application/json")
	public @ResponseBody String	decrypt(@RequestBody Map<String, Object> tokenInput) throws JSONException {
		JSONObject json = new JSONObject();
		try {
			if (tokenInput != null 
			&& !tokenInput.isEmpty()
			&& tokenInput.get("token") != null) {
				String token = (String) tokenInput.get("token");
				String value = HashMapCache.get(token);
				if (value != null) {
					json = new JSONObject(CryptUtil.decrypt(value));
				}
			} else {
				json.put("error", "empty token!");
			}
		} catch (Exception e) {
			json.put("error", e.getMessage());
		}
		return json.toString();
	}
	
	private synchronized String getToken(JSONObject body) {
		String token = null;
		do {
			token = RandomString.randomString(8);
		} while (HashMapCache.containsKey(token));
		HashMapCache.put(token, CryptUtil.encrypt(body.toString()));
		return token;
	}
}
