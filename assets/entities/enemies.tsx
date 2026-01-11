<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.11.2" name="enemies" class="enemy" tilewidth="41" tileheight="63" tilecount="3" columns="0">
 <grid orientation="orthogonal" width="1" height="1"/>
 <tile id="0" type="player">
  <properties>
   <property name="Class" value="player"/>
   <property name="health" type="int" value="3"/>
  </properties>
  <image source="attack_left_00.png" width="32" height="32"/>
 </tile>
 <tile id="1" type="enemy">
  <image source="oak_tree.png" width="41" height="63"/>
 </tile>
 <tile id="3" type="heart">
  <image source="heart.png" width="16" height="16"/>
 </tile>
</tileset>
