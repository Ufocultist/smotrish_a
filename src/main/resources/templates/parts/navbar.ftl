<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#9900FF">
    <a class="navbar-brand" href="/">SMOTRI.SH</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">

        <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
        </li>

    <#if isAdmin>
        <li class="nav-item">
            <a class="nav-link" href="/main">Channels</a>
        </li>
    </#if>

        <li class="nav-item">
            <a class="nav-link" href="/user-channels/${currentUserId}">My Channels</a>
        </li>

    <#if isAdmin>
        <li class="nav-item">
            <a class="nav-link" href="/user">Users</a>
        </li>
    </#if>

        <li class="nav-item">
            <a class="nav-link" href="/user/profile">Profile</a>
        </li>

    <#if !user??>
        <li class="nav-item">
            <a class="nav-link" href="/registration">Registration</a>
        </li>
    </#if>

    </ul>
<#if user??>
    <div class="navbar-text mr-3">${name}</div>
    <@l.logout />
</div>
</#if>
</nav>