<%@ page import="webstore.Wishlist" %>



<div class="fieldcontain ${hasErrors(bean: wishlistInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="wishlist.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${webstore.User.list()}" optionKey="id" required="" value="${wishlistInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wishlistInstance, field: 'wishlist', 'error')} ">
	<label for="wishlist">
		<g:message code="wishlist.wishlist.label" default="Wishlist" />
		
	</label>
	<g:select name="wishlist" from="${webstore.Book.list()}" multiple="multiple" optionKey="id" size="5" value="${wishlistInstance?.wishlist*.id}" class="many-to-many"/>
</div>

