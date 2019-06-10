<#import "parts/common.ftl" as c>

<@c.page>
<div>
    <h5>User Editor</h5>
</div>
<form action="/user" method="post">
    <table>
        <tr>
            <td><label path="username">UserName</label></td>
            <td><input type="text" name="username" value="${user.username}"></td>
        </tr>
        <tr>
            <td><label path="firstname">FirstName</label></td>
            <td><input type="text" name="firstname" value="${user.firstname}"></td>
        </tr>
        <tr>
            <td><label path="lastname">LastName</label></td>
            <td><input type="text" name="lastname" value="${user.lastname}"></td>
        </tr>
        <tr>
            <td><label path="emial">Email</label></td>
            <td><input type="text" name="email" value="${user.email}"></td>
        </tr>
        <tr>
            <td><label path="active">Active</label></td>
            <td><input type="text" name="active" value="${user.active?c}"></td>
        </tr>
        <tr>
            <td><label>Roles</label></td>
            <td>
                <#list roles as role>
                    <div>
                        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                    </div>
                </#list>
            </td>
        </tr>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </table>
    <button type="submit">Save changes</button>

</form>
</@c.page>