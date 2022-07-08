package com.example.service;

import com.example.entity.Univercity;
import com.example.payload.ApiResponse;
import com.example.payload.UnivercityDto;

import java.util.List;

public interface UnivercityService {

    ApiResponse addUniversity(UnivercityDto univercityDto);

    List<Univercity> getAllUnivercity();

    Univercity getByUniercityId(Integer id);

    ApiResponse editUniversity(Integer id, UnivercityDto univercityDto);

    ApiResponse deleteUnivercity(Integer id);
}
