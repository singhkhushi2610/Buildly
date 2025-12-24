package com.khushi.projects.buildly.service;

import com.khushi.projects.buildly.dto.member.InviteMemberRequest;
import com.khushi.projects.buildly.dto.member.MemberResponse;
import com.khushi.projects.buildly.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, UpdateMemberRoleRequest request, Long memberId, Long userId);

    void removeProjectMember(Long projectId, Long memberId, Long userId);
}
