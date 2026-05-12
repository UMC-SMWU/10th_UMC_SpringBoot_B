package com.example.umc10thsb.domain.mission.converter;

import com.example.umc10thsb.domain.member.entity.Member;
import com.example.umc10thsb.domain.mission.dto.MissionReqDTO;
import com.example.umc10thsb.domain.mission.dto.MissionResDTO;
import com.example.umc10thsb.domain.mission.entity.Mission;
import com.example.umc10thsb.domain.mission.entity.mapping.MemberMission;
import com.example.umc10thsb.domain.mission.enums.Address;
import com.example.umc10thsb.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public class MissionConverter {

    private MissionConverter() {}

    // 미션 생성 요청 DTO + 가게 → Mission 엔티티
    public static Mission toMission(MissionReqDTO.CreateMission req, Store store) {
        return Mission.builder()
                .store(store)
                .title(req.title())
                .content(req.content())
                .reward(req.reward())
                .deadline(LocalDate.parse(req.deadline()))
                .build();
    }

    // 미션 생성 응답
    public static MissionResDTO.CreateMission toCreateMissionRes(Mission mission) {
        return MissionResDTO.CreateMission.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .title(mission.getTitle())
                .build();
    }

    // 회원-미션 매핑
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }

    // MemberMission → 미션 목록 element
    public static MissionResDTO.MissionItem toMissionItem(MemberMission mm) {
        Mission m = mm.getMission();
        return MissionResDTO.MissionItem.builder()
                .missionId(m.getId())
                .storeName(m.getStore().getName())
                .title(m.getTitle())
                .content(m.getContent())
                .reward(m.getReward())
                .status(mm.getStatus().name())
                .deadline(m.getDeadline() != null ? m.getDeadline().toString() : null)
                .build();
    }

    // Page<MemberMission> → 페이지네이션된 응답
    public static MissionResDTO.MissionList toMissionList(Page<MemberMission> page) {
        List<MissionResDTO.MissionItem> items = page.getContent().stream()
                .map(MissionConverter::toMissionItem)
                .toList();

        return MissionResDTO.MissionList.builder()
                .missions(items)
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .hasNext(page.hasNext())
                .build();
    }

    // 미션 상세
    public static MissionResDTO.MissionDetail toMissionDetail(Mission mission, MemberMission memberMission) {
        return MissionResDTO.MissionDetail.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .title(mission.getTitle())
                .content(mission.getContent())
                .reward(mission.getReward())
                .status(memberMission != null ? memberMission.getStatus().name() : null)
                .createdAt(mission.getCreatedAt() != null ? mission.getCreatedAt().toString() : null)
                .deadline(mission.getDeadline() != null ? mission.getDeadline().toString() : null)
                .build();
    }

    // 미션 완료 처리 응답
    public static MissionResDTO.CompleteMission toCompleteMissionRes(MemberMission mm, int rewardedPoint, int totalPoint) {
        return MissionResDTO.CompleteMission.builder()
                .missionId(mm.getMission().getId())
                .status(mm.getStatus().name())
                .rewardedPoint(rewardedPoint)
                .totalPoint(totalPoint)
                .build();
    }

    // 홈 화면 - 동네별 미션 진행률
    public static MissionResDTO.HomeMissions toHomeMissions(Address address,
                                                            long completedCount,
                                                            long totalCount,
                                                            List<MemberMission> challenging) {
        List<MissionResDTO.MissionItem> items = challenging.stream()
                .map(MissionConverter::toMissionItem)
                .toList();

        return MissionResDTO.HomeMissions.builder()
                .address(address.name())
                .completedCount(completedCount)
                .totalCount(totalCount)
                .missions(items)
                .build();
    }
}
