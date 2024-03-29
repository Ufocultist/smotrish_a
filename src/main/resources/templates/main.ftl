<#import "parts/common.ftl" as c>

<@c.page>
<div class="dorm-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by Name">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

    <#include "parts/channelsEdit.ftl" />
    <#include "parts/channelsList.ftl" />

</@c.page>