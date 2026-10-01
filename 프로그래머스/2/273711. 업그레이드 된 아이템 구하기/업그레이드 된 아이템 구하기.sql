-- 코드를 작성해주세요
SELECT child.ITEM_ID, child.ITEM_NAME, child.RARITY
FROM ITEM_TREE t
JOIN ITEM_INFO parent
    ON parent.ITEM_ID = t.PARENT_ITEM_ID
JOIN ITEM_INFO child
    ON child.ITEM_ID = t.ITEM_ID
WHERE parent.RARITY = 'RARE'
ORDER BY child.ITEM_ID DESC