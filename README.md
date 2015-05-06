### Format des fichiers de configs
#### Concepts :
+ Blacklist
+ Expression régulières (On laisseras le choix sur le logiciel / site)
+ XML
+ ...

#### Exemple possible (XML) :

```xml
<srtcal>
	<title>Master RO</title>
	
	<schedules>
		<schedule type="celcat" url="http://...">
			<blacklist>
				<item type="regexp"><![[CDATA Programmation *$ ]]></item>
				<item type="text"><![[CDATA Coman etr 1 pro ss lol pr shoutix ]]></item>
			</blacklist>
		</schedule>
		<schedule type="adeweb" url="http://....">
			<properties>
				<!-- On vas surement en need dans quelques cas -->
			</properties>
			<blacklist>
			</blacklist>
		</schedule>
	</schedules>
</srtcal>
```

Et vu qu'il veux des "Super groupes" :

```xml
<calgroup>
	<srtcal>
		<title>Master SWEGG</title>
		
		...
	</srtcal>
	<srtcal>
		<title>Master LEL</title>
		
		...
	</srtcal>
	...
</calgroup>
```

### Liste des liens importants 
[Efficient UI](https://www.youtube.com/watch?v=N6YdwzAvwOA), plutôt interessant et utile.  
[GWT-RCP](https://docs.google.com/document/d/1eG0YocsYYbNAtivkLtcaiEE5IOF5u4LUol8-LL0TIKU/edit) : Protocol pour ade web

### TODO :
