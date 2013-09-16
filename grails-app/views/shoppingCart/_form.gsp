<%@ page import="webstore.ShoppingCart" %>



<div class="fieldcontain ${hasErrors(bean: shoppingCartInstance, field: 'cart', 'error')} ">
	<label for="cart">
		<g:message code="shoppingCart.cart.label" default="Cart" />
		
	</label>
	<g:select name="cart" from="${webstore.BookOrder.list()}" multiple="multiple" optionKey="id" size="5" value="${shoppingCartInstance?.cart*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shoppingCartInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="shoppingCart.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${webstore.User.list()}" optionKey="id" required="" value="${shoppingCartInstance?.customer?.id}" class="many-to-one"/>
</div>

