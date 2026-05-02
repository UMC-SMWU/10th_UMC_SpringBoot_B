package com.example.chap04.domain.location.controller;

import com.example.chap04.domain.location.dto.LocationResponse;
import com.example.chap04.domain.member.service.MemberService;
import com.example.chap04.domain.mission.dto.MissionResponseDTO;
import com.example.chap04.global.ApiResponse;
import com.example.chap04.global.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {
    private final MemberService memberService;

    @GetMapping("/locations/{memberId}")
    public ResponseEntity<ApiResponse<LocationResponse.MyLocation>> getLocations (
        @PathVariable Long memberId
    ) {
        LocationResponse.MyLocation result = memberService.getLocations(memberId);
        return ApiResponse.onSuccessResponse(GeneralSuccessCode.GET_SUCCESS, result);
    }
}
