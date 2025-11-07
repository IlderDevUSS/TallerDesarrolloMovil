package com.example.tdama_202502_jecm.Interface;
import com.example.tdama_202502_jecm.Model.RequestAuth;
import com.example.tdama_202502_jecm.Model.ResponseAuth;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ByRedliPyAnyApi {
    @POST("auth")
    Call<ResponseAuth> obtenerToken(@Body RequestAuth requestAuth);
}
