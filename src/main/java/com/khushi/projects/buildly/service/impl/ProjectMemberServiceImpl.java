package com.khushi.projects.buildly.service.impl;

import com.khushi.projects.buildly.dto.member.InviteMemberRequest;
import com.khushi.projects.buildly.dto.member.MemberResponse;
import com.khushi.projects.buildly.dto.member.UpdateMemberRoleRequest;
import com.khushi.projects.buildly.entity.Project;
import com.khushi.projects.buildly.entity.ProjectMember;
import com.khushi.projects.buildly.entity.ProjectMemberId;
import com.khushi.projects.buildly.entity.User;
import com.khushi.projects.buildly.error.ResourceNotFoundException;
import com.khushi.projects.buildly.mapper.MemberMapper;
import com.khushi.projects.buildly.repository.ProjectMemberRepository;
import com.khushi.projects.buildly.repository.ProjectRepository;
import com.khushi.projects.buildly.repository.UserRepository;
import com.khushi.projects.buildly.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    MemberMapper memberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();

        List<MemberResponse> memberResponseList = projectMemberRepository
                .findByIdProjectId(projectId)
                .stream()
                .map(memberMapper::toMemberResponseFromMember)
                .toList();

        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();
        if(!invitee.getId().equals(userId))
            throw new RuntimeException("You cannot invite yourself!");

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
        if(projectMemberRepository.existsById(projectMemberId))
            throw new RuntimeException("User already exists as a Project Member!");

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.projectRole())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);
        return memberMapper.toMemberResponseFromMember(projectMember);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, UpdateMemberRoleRequest request, Long memberId, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);
        return memberMapper.toMemberResponseFromMember(projectMember);

    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow(
                () -> new ResourceNotFoundException(projectId.toString(), "project")
        );

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId))
            throw new RuntimeException("User is not a member of this Project!");

        projectMemberRepository.deleteById(projectMemberId);

    }
}
