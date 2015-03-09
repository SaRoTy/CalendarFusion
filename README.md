# Projet de TER L3

## Partie 1 : Projet Android.

### Celcat

Pour cellcat, on a toujours un fichier xml (qui est mis en forme à l'aide d'un fichier xsl, qu'on peut ignorer).

La node principale s'appele timetable (oui on s'en branle :D).
On a ensuite une node __option__ formaté come ceci : 

```xml
<option combined="0" totalweeks="0" showemptydays="1" dayclass="reverse">
    <link href="g558.xml" class="xml">Imprimer</link>
    <weeks>Semaines</weeks>
    <dates>Dates</dates>
    <week>Semaine</week>
    <date>Date</date>
    <all>Toutes les semaines</all>
    <notes>Remarques</notes>
    <id>ID</id>
    <tag>Champs Libre</tag>
    <footer>
        Publié le 06/03/2015 07:18:23 - Université Toulouse III -  Paul Sabatier
        <br />
        Créé par
        <a href="http://www.celcat.com" target="_parent">CELCAT Timetabler Web Publisher</a>
      </footer>
    <subheading>Emploi du temps Groupe - L3 info - S1 - G 2.1</subheading>
</option>
```

(Ces options sont pour le langage surtout, mais on les utiliseras pour faire plus *propre* ^_^)

La suite consiste en une succesion de node de type __span__ :

```xml
<span id="0" date="02/03/2015" rawix="29" rawlen="1">
    <description>Sem 10, commençant le 02/03/2015</description>
    <title>10</title>
    <alleventweeks>NNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNN</alleventweeks>
    <day id="0">
        <name>lundi</name>
    </day>
    <day id="1">
        <name>mardi</name>
    </day>
    <day id="2">
        <name>mercredi</name>
    </day>
    <day id="3">
        <name>jeudi</name>
    </day>
    <day id="4">
        <name>vendredi</name>
    </day>
</span>
```

Elle servent à crée la structure de l'emplois du temps.

Enfin, le plus important, les nodes de type event :

```xml
<event id="58055" colour="#D3D3FF">
   <day>0</day>
   <prettytimes>07:45-09:45</prettytimes>
   <starttime>07:45</starttime>
   <endtime>09:45</endtime>
   <category>COURS</category>
   <prettyweeks />
   <rawweeks>NNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNN</rawweeks>
   <resources>
      <module>
         <item>MATHEMATIQUES</item>
      </module>
      <room>
         <item>FSI / U4-202</item>
      </room>
      <group>
         <item>L3 info - section 1</item>
      </group>
   </resources>
</event>
```

### TODO :


### Autre
Pour modifier ce fichier : [Markdown](http://fr.wikipedia.org/wiki/Markdown)
