package com.example.project;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.project.Model.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class HealthCareServiceApplicationTests {

	public static String token;
	public static String patientOneId;
	public static String patientTwoId;
	public static String appointmentOneId;
	public static String appointmentTwoId;
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void insertIntoDB() throws Exception {
		System.out.println("1.............");
		JSONObject json = new JSONObject();
		json.put("user_email", "user@hcs.com");
		json.put("user_name", "userOne");
		json.put("password", "user@1");
		json.put("user_mobile", "9876543210");
		json.put("location", "Chennai");
		mockMvc.perform(MockMvcRequestBuilders.post("/register").content(toJson(json))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("registration successful"));
	}

	@Test
	@Order(2)
	public void loginUser() throws Exception {
		System.out.println("2.............");
		JSONObject json = new JSONObject();
		json.put("user_name", "userOne");
		json.put("password", "user@1");
		MvcResult result = mockMvc
				.perform(post("/signin").content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.token", notNullValue())).andReturn();
		json = (JSONObject) new org.json.simple.parser.JSONParser().parse(result.getResponse().getContentAsString());
		token = json.get("token").toString();
	}

	@Test
	@Order(3)
	public void test1() throws Exception {
		ApplicationUser u = new ApplicationUser("user1", "user1@hcs.com", "user@1", "9999999999", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void test2() throws Exception {
		ApplicationUser u = new ApplicationUser("use", "user1@hcs.com", "user@1", "9999999999", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	@Order(5)
	public void test4() throws Exception {
		ApplicationUser u = new ApplicationUser("user123", "user1#hcs.com", "user@1", "9999999999", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	@Order(6)
	public void test5() throws Exception {
		ApplicationUser u = new ApplicationUser("user123", "user1@hcs.com", "user@1", "4999999999", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	@Order(7)
	public void test6() throws Exception {
		ApplicationUser u = new ApplicationUser("user123", "user1@hcs.com", "user@1", "999999999", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	@Order(8)
	public void test7() throws Exception {
		ApplicationUser u = new ApplicationUser("userone", "user1@hcs.com", "user@1", "99999999999", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	@Order(9)
	public void test8() throws Exception {
		ApplicationUser u = new ApplicationUser("", "user1@hcs.com", "user@1", "9999999999", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	@Order(10)
	public void test10() throws Exception {
		ApplicationUser u = new ApplicationUser("userone", "user1@hcs.com", "user@1", "", "chennai");
		byte[] iJson = toJson(u);
		mockMvc.perform(post("/register").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	@Order(11)
	public void test11() throws Exception {
		ApplicationUser u = new ApplicationUser("userOne", "user@1");
		byte[] iJson = toJson(u);
		System.out.println(iJson[0]);
		System.out.println(iJson[1]);
		MvcResult result = mockMvc.perform(post("/signin").content(iJson).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
	}

	@Test
	@Order(12)
	public void test12() throws Exception {
		ApplicationUser u = new ApplicationUser("userOne", "user@2");
		byte[] iJson = toJson(u);
		try {
			mockMvc.perform(post("/signin").content(iJson).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		} catch (Exception e) {
			assertEquals(e.getCause().getClass().getSimpleName(), "BadCredentialsException");
		}
	}

	@Test
	@Order(13)
	public void test13() throws Exception {
		mockMvc.perform(get("/viewprofile/userOne").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.user_name", is("userOne")));
	}

	@Test
	@Order(14)
	public void test14() throws Exception {
		JSONObject json = new JSONObject();
		json.put("user_email", "updateduser@tcs.com");
		json.put("location", "Mumbai");
		mockMvc.perform(MockMvcRequestBuilders.put("/editprofile/userOne").header("Authorization", "Bearer " + token)
				.content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		mockMvc.perform(get("/viewprofile/userOne").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.location", is("Mumbai")));
	}

	@Test
	@Order(15)
	public void test15() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patient_name", "patientOne");
		json.put("patient_email", "patientone@gmail.com");
		json.put("patient_dob", "2000-02-20T00:00:00.000+0000");
		json.put("patient_location", "Rajasthan");
		json.put("patient_gender", "Male");
		json.put("patient_mobile", "9889766754");
		MvcResult result = mockMvc
				.perform(post("/patients/register").header("Authorization", "Bearer " + token)
						.content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.patient_Id", notNullValue())).andReturn();
		json = (JSONObject) new org.json.simple.parser.JSONParser().parse(result.getResponse().getContentAsString());
		patientOneId = json.get("patient_Id").toString();
	}

	@Test
	@Order(16)
	public void test16() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patient_name", "patientTwo");
		json.put("patient_email", "patienttwo@gmail.com");
		json.put("patient_dob", "2000-03-20T00:00:00.000+0000");
		json.put("patient_location", "Punjab");
		json.put("patient_gender", "Female");
		json.put("patient_mobile", "7654321987");
		MvcResult result = mockMvc
				.perform(post("/patients/register").header("Authorization", "Bearer " + token)
						.content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.patient_Id", notNullValue())).andReturn();
		json = (JSONObject) new org.json.simple.parser.JSONParser().parse(result.getResponse().getContentAsString());
		patientTwoId = json.get("patient_Id").toString();
	}

	@Test
	@Order(17)
	public void test17() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patient_name", "pat");
		json.put("patient_email", "patientone@gmail.com");
		json.put("patient_dob", "2000-02-20T00:00:00.000+0000");
		json.put("patient_location", "Rajasthan");
		json.put("patient_gender", "Male");
		json.put("patient_mobile", "9889766754");
		mockMvc.perform(
				post("/patients/register").header("Authorization", "Bearer " + token).content(json.toJSONString())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(18)
	public void test18() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patient_name", "patient");
		json.put("patient_email", "patientone#gmail.com");
		json.put("patient_dob", "2000-02-20T00:00:00.000+0000");
		json.put("patient_location", "Rajasthan");
		json.put("patient_gender", "Male");
		json.put("patient_mobile", "9889766754");
		mockMvc.perform(
				post("/patients/register").header("Authorization", "Bearer " + token).content(json.toJSONString())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(19)
	public void test19() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patient_name", "pat");
		json.put("patient_email", "patientone@gmail.com");
		json.put("patient_dob", "2000-02-20T00:00:00.000+0000");
		json.put("patient_location", "Rajasthan");
		json.put("patient_gender", "Others");
		json.put("patient_mobile", "9889766754");
		mockMvc.perform(
				post("/patients/register").header("Authorization", "Bearer " + token).content(json.toJSONString())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(20)
	public void test20() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patient_name", "pat");
		json.put("patient_email", "patientone@gmail.com");
		json.put("patient_dob", "2000-02-20T00:00:00.000+0000");
		json.put("patient_location", "Rajasthan");
		json.put("patient_gender", "Male");
		json.put("patient_mobile", "4567890123");
		mockMvc.perform(
				post("/patients/register").header("Authorization", "Bearer " + token).content(json.toJSONString())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(21)
	public void test21() throws Exception {
		mockMvc.perform(get("/patients/view/" + patientTwoId).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.patient_name", is("patientTwo")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.patient_Id", is(patientTwoId)));
	}

	@Test
	@Order(22)
	public void test22() throws Exception {
		mockMvc.perform(get("/patients/list").header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].patient_name", is("patientOne")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].patient_Id", is(patientOneId)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].patient_name", is("patientTwo")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].patient_Id", is(patientTwoId)));
	}

	@Test
	@Order(23)
	public void test23() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patientId", patientOneId);
		json.put("disease", "Fever");
		json.put("tentativeDate", "2021-05-20T00:00:00.000+0000");
		json.put("priority", "High");
		json.put("description", "Having fever for 3 days");
		MvcResult result = mockMvc
				.perform(post("/appointment/register").header("Authorization", "Bearer " + token)
						.content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.booking_id", notNullValue()))
				.andReturn();
		json = (JSONObject) new org.json.simple.parser.JSONParser().parse(result.getResponse().getContentAsString());
		appointmentOneId = json.get("booking_id").toString();
	}

	@Test
	@Order(24)
	public void test24() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patientId", patientTwoId);
		json.put("disease", "Stomach ache");
		json.put("tentativeDate", "2021-05-21T00:00:00.000+0000");
		json.put("priority", "Low");
		json.put("description", "Having stomach-ache since 7 days");
		MvcResult result = mockMvc
				.perform(post("/appointment/register").header("Authorization", "Bearer " + token)
						.content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.booking_id", notNullValue()))
				.andReturn();
		json = (JSONObject) new org.json.simple.parser.JSONParser().parse(result.getResponse().getContentAsString());
		appointmentTwoId = json.get("booking_id").toString();
	}

	@Test
	@Order(25)
	public void test25() throws Exception {
		JSONObject json = new JSONObject();
		json.put("patientId", patientTwoId);
		json.put("disease", "Stomach ache");
		json.put("tentativeDate", "2021-05-21T00:00:00.000+0000");
		json.put("priority", "Hard");
		json.put("description", "Having stomach-ache since 7 days");
		mockMvc.perform(
				post("/appointment/register").header("Authorization", "Bearer " + token).content(json.toJSONString())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	@Order(26)
	public void test26() throws Exception {
		mockMvc.perform(get("/appointment/list/" + appointmentOneId).header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.patientId", is(patientOneId)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.booking_id", is(appointmentOneId)));
	}

	@Test
	@Order(27)
	public void test27() throws Exception {
		mockMvc.perform(get("/appointment/list").header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].patientId", is(patientOneId)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].booking_id", is(appointmentOneId)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].patientId", is(patientTwoId)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].booking_id", is(appointmentTwoId)));
	}

	@Test
	@Order(28)
	public void test28() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/appointment/delete/" + appointmentTwoId).header("Authorization",
				"Bearer " + token)).andExpect(status().isOk());
		mockMvc.perform(get("/appointment/list").header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
	}

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}

}
