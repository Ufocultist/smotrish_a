<#import "parts/common.ftl" as c>

<@c.page>
<div>
<h5>Users List</h5
</div>
<table class="table table-bordered">
    <thread>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">UserName</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Email</th>
        <th scope="col">Active</th>
        <th scope="col">Role</th>
        <th scope="col"></th>
        <th scope="col">Delete</th>
    </tr>
    </thread>
    <tbody>
<#list users as user>
    <tr>
    <td>${user.id}</td>
    <td>${user.username}</td>
    <td>${user.firstname}</td>
    <td>${user.lastname}</td>
    <td>${user.email}</td>
    <td>${user.active?c}</td>
    <td><#list user.roles as role>${role}<#sep>, </#list></td>
    <td><a href="/user/${user.id}">Edit</a></td>
    <td><a href="/user/delete/${user.id}">Delete</a></td>
    </tr>
</#list>
    </tbody>
</table>
</@c.page>