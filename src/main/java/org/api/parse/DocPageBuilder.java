package org.api.parse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.api.doc.bean.ApiDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.spec.example.SpecExampleExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.KeepType;
import com.vladsch.flexmark.util.options.MutableDataSet;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class DocPageBuilder {

	// private static final Logger log =
	// LoggerFactory.getLogger(DocPageBuilder.class);

	private static final String FILE_DOC_MD = "doc.md.ftl";

	@Autowired
	private ClasspathFreeMarker freeMarker;

	private Template mdTemplate = null;

	private Parser parser = null;

	private HtmlRenderer renderer = null;

	@PostConstruct
	public void init() {
		try {
			mdTemplate = freeMarker.getTemplate(DocPageBuilder.class, FILE_DOC_MD);
			MutableDataSet options = new MutableDataSet();
			options.setFrom(ParserEmulationProfile.GITHUB_DOC);
			options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), SpecExampleExtension.create()));

			// References compatibility
			options.set(Parser.REFERENCES_KEEP, KeepType.LAST);

			// Set GFM table parsing options
			options.set(TablesExtension.COLUMN_SPANS, false) //
					.set(TablesExtension.MIN_HEADER_ROWS, 1) //
					.set(TablesExtension.MAX_HEADER_ROWS, 1) //
					.set(TablesExtension.APPEND_MISSING_COLUMNS, true) //
					.set(TablesExtension.DISCARD_EXTRA_COLUMNS, true) //
					.set(TablesExtension.WITH_CAPTION, false) //
					.set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true);

			// Setup List Options for GitHub profile which is kramdown for documents
			options.setFrom(ParserEmulationProfile.GITHUB_DOC);

			// You can re-use parser and renderer instances
			parser = Parser.builder(options).build();
			renderer = HtmlRenderer.builder(options).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String md2Html(String md) throws Exception {
		Node document = parser.parse(md);
		String html = renderer.render(document);
		return html;
	}

	public String doc2Md(ApiDetail apiBean) {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("api", apiBean);
			String content = freeMarker.build(mdTemplate, model);
			return content;
		} catch (IOException | TemplateException e) {
			throw new RuntimeException(e);
		}
	}

	public String loadMdFromResource(String path) throws Exception {
		if (StringUtils.isEmpty(path)) {
			throw new NullPointerException("path is null.");
		}
		path = path.trim();
		if (!path.startsWith("/")) {
			path = "/" + path;
		}

		//ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String md = ""; // Strings.getResourceByPath(path, loader);
		return md;
	}

	public String loadMdFromResource(String folderId, String docId) throws Exception {
		String fileName = "1";
		String path = folderId + "/" + fileName;
		String md = loadMdFromResource(path);
		return md;
	}

}
