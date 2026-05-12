package com.example.chap04.domain.mission.controller;

import com.example.chap04.domain.mission.dto.MemberMissionRequestDTO;
import com.example.chap04.domain.mission.dto.MemberMissionResponseDTO;
import com.example.chap04.domain.mission.service.MemberMissionService;
import com.example.chap04.global.api.ApiResponse;
import com.example.chap04.global.api.GeneralSuccessCode;
import com.example.chap04.global.common.paging.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member-missions")
@RequiredArgsConstructor
public class MemberMissionController {
    private final MemberMissionService memberMissionService;

    @PostMapping("/my-ongoing")
    public ResponseEntity<ApiResponse<PageResponse<MemberMissionResponseDTO.MyMissionResponseDto>>> getMyMissions(
            @RequestBody @Valid MemberMissionRequestDTO.MyMissionRequestDto request,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        PageResponse<MemberMissionResponseDTO.MyMissionResponseDto> result =
                memberMissionService.getMyMissions(request, page, size);

        return ApiResponse.onSuccessResponse(
                GeneralSuccessCode.POST_SUCCESS,
                result
        );
    }

}
