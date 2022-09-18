package ru.community;

import java.io.File;
import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ru.community.database.repos.Gallery_repo;
import ru.community.database.repos.customUser_repo;
import ru.community.database.repos.nametags;
import ru.community.database.repos.posts_repo;
import ru.community.database.units.Image;
import ru.community.database.units.nametag;
import ru.community.database.units.post;


//Основной контроллер, почти вся логика приложения
//Controller that has full application logic
@Transactional
@Controller
public class PostingController {
	private posts_repo posts;
	@Autowired
	public void setPosts(posts_repo posts) {this.posts = posts;}
	@Autowired
	public void setCur(customUser_repo cur) {this.cur = cur;}
	private static customUser_repo cur;
	@Autowired
	public void setGallery(Gallery_repo gallery_repo) {this.gallery_repo = gallery_repo;}
	private Gallery_repo gallery_repo;
	@Autowired
	public void setNametagsl(nametags nametags) {
		this.nametags = nametags;
	}
	private nametags nametags;
	
	//лист из всех постов
	//all posts list
	@RequestMapping(value="/")
	public String seePosts(Model model) {
		Iterable<post> postlist=posts.findAll();
		model.addAttribute("postlist",postlist);
		
		
		return "/posts-page";
	}
	//создание нового поста - страница с формой
	//form of adding post
	@RequestMapping(value="/createpost", method=RequestMethod.GET)
	public String createPost() {
		return "create-post-form";
	}
	//пост метод, для создания поста в бд и тд
	//post method, for adding post to DB
	@RequestMapping(value="/createpost", method=RequestMethod.POST)
	public String createPostControll(Principal principal,
			@RequestParam(name="name")String name,
			@RequestParam(name="context")String context) {
		if (posts.findByName(name)!=null) { return "fault-page";}
		else {posts.save(new post(name,context,principal.getName())); }
		return "redirect:/";
	}
	//контекст поста, его теги, изображения | (нужно сделать привязанные файлы)
	//page of one post, tags,images etc
	@RequestMapping(value="/see-post/{name}")
	public String seepost(@PathVariable("name") String name, Principal principal, Model model) {
		
		post a = posts.findByName(name);
		if (a != null) {
			
		if (principal.getName().equals(a.getOwnername())) {
			model.addAttribute("isOwner", "yes");
		}
		else {
			model.addAttribute("isOwner", "no");
		}
		model.addAttribute("post",a);
		model.addAttribute("gallery", gallery_repo.findAllByParentName(name));
		model.addAttribute("tags",nametags.findAllByOwnername(name));
		model.addAttribute("owner",cur.findByUsername(principal.getName()).getVisibleName());
		
		return "see-post";
		} 
		//||||||||||||||||||||||||||||||||||
		else {return "fault-page";}
	}
	//метод добавления фотографии, которую можно добавить создателю поста, на странице поста
	//method to add image
	@RequestMapping(value="/add-image", method=RequestMethod.POST)
	public String addimage(
			@RequestParam(name="postname") String name,
			@RequestParam(name="img") MultipartFile file,
			Principal principal,Model model) {
		if(file !=null) {
		post a = posts.findByName(name);
		if(a.getOwnername().equals(principal.getName())) {
			Image b = new Image(name, "image\\"+name+file.getOriginalFilename());
			gallery_repo.save(b);
			b.saveImage(name,file);
			
			return"redirect:/see-post/"+name;}
		
		else {return "fault-page";} }
		else {return"redirect:/see-post/"+name;}
	}
	//метод добавления тега, работает также как и добавление фото
	//method to add tag
	@RequestMapping(value="/add-tag", method=RequestMethod.POST)
	public String addtag(Principal principal,
			@RequestParam(name="postname") String postname,
			@RequestParam(name="tag") String tag) {
		if(posts.findByName(postname).getOwnername().equals(principal.getName())) {
				nametags.save(new nametag(tag,postname));
		}
	return "redirect:/see-post/"+postname;	
	}
	//удаление тега, нужно нажать рядом с тегом, только для создателя поста
	//deleting tag method
	@RequestMapping(value="/delete-tag", method=RequestMethod.POST)
	public String deletetag(Principal principal,
			@RequestParam(name="postname") String postname,
			@RequestParam(name="name") String tag) {
		if(posts.findByName(postname).getOwnername().equals(principal.getName())) {
			nametags.deleteByNameAndOwnername(tag,postname);
			return "redirect:/see-post/"+postname;
		}else {
		return "fault-page";
			}
		}
	
	public static customUser_repo customUser_repo() {
		return cur;
	}
	//удаление поста, только для создателя поста
	//deleting post method
	@RequestMapping(value="/delete-post", method=RequestMethod.POST)
	public String deletepost(@RequestParam(name="postname") String postname,
			Principal principal, Model model) {
		if(principal.getName().equals(posts.findByName(postname).getOwnername())) {
			
			posts.deleteByName(postname);
			gallery_repo.deleteAllByParentName(postname);
			//delete all attachments etc
			model.addAttribute(postname);
			return "redirect:/";
		}
		return"fault-page";
	}
	
}