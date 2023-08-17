package github.wozniak.bankingservice.controller;

import github.wozniak.bankingservice.entity.BankAccount;
import github.wozniak.bankingservice.request.RegistrationRequest;
import github.wozniak.bankingservice.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Jackson Wozniak
 * @created : 8/16/2023, Wednesday
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class BankingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BankAccountService bankAccountService;

    private static final String root = "/api/v1/banking";
    private static final String testPassword = "1Qq()TEST";

    @Test
    void createAccount() throws Exception {
        BankAccount test = new BankAccount(randomUsername(), testPassword);
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                .post(root + "/account")
                        .content(new RegistrationRequest(test.getUsername(), test.getPassword()).asJsonString())
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String str = res.getResponse().getContentAsString();
        assertNotNull(str);

        //TODO:
        //get account for testing & then delete account before ending test
        //delete regardless of test results
    }

    private void getAccount(String token) throws Exception{
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                        .get(root + "/account?token= " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String str = res.getResponse().getContentAsString();
        assertNotNull(str);
    }

    private void deleteAccount(String token) throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                        .delete(root + "/account?token= " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String str = res.getResponse().getContentAsString();
        assertNotNull(str);
    }

    public String randomUsername(){
        for(int i = 0; i < 1_000_000; i++){
            if(!bankAccountService.accountExists(String.valueOf(i))){
                return String.valueOf(i);
            }
        }
        throw new RuntimeException("Test failed: no username");
    }
}