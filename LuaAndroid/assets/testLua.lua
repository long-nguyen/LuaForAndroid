function testAdd(a,b)
print("-----------------------------------------",a,b)
	return a+b
end

function testObject(v)
	v:increase();			--Goi cac ham cua doi tuong v
	v:increase();
	print("Inlua :"..v:get());
	return v;
end